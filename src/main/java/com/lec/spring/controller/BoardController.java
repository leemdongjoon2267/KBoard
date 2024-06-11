package com.lec.spring.controller;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.PostValidator;
import com.lec.spring.service.BoardService;
import com.lec.spring.util.U;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardController(){
        System.out.println("BoardController() 생성");
    }

    @GetMapping("/write")
    public void write(){} // view 리턴

    @PostMapping("/write")
    public String writeOk(
            @RequestParam Map<String, MultipartFile> files // 첨부파일들 <name, file>
            ,@Valid Post post
            , BindingResult result
            , Model model    // 매개변수 선언시 BindingResult 보다 Model 을 뒤에 두어야 한다.
            , RedirectAttributes redirectAttrs   // redirect 시 넘겨줄 값들을 담는 객체
    ){

        // 만약 에러가 있다면 redirect 한다!
        if(result.hasErrors()){

            // addAttribute
            //    request parameters로 값을 전달.  redirect URL에 query string 으로 값이 담김
            //    request.getParameter에서 해당 값에 액세스 가능
            // addFlashAttribute
            //    일회성. 한번 사용하면 Redirect후 값이 소멸
            //    request parameters로 값을 전달하지않음
            //    '객체'로 값을 그대로 전달

            // redirect 시, 기존에 입력샜던 값들은 보이게 하기
            redirectAttrs.addFlashAttribute("user", post.getUser());
            redirectAttrs.addFlashAttribute("subject", post.getSubject());
            redirectAttrs.addFlashAttribute("content", post.getContent());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/write";
        }


        model.addAttribute("result", boardService.write(post, files));
        return "board/writeOk";
    }


    @GetMapping("/detail/{id}")
    public String detial(@PathVariable Long id, Model model){

        Post post = boardService.detail(id);
        model.addAttribute("post", post); // post 라는 이름으로 담아서 넘김

        return "board/detail"; // view 리턴

    }

    @GetMapping("/list")
  //    public void list(Model model){
        public void list(Integer page, Model model){
        boardService.list(page, model);
//      model.addAttribute("list", boardService.list());
    }


    // @PathVariable : URL 경로에서 id 값을 추출하여 메소드의 파라미터로 전달
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("post", boardService.selectById(id));
        return "board/update";
    }

    @PostMapping("/update")
    public String updateOk(
            @RequestParam Map<String, MultipartFile> files // 새로 추가될 파일들
            , @Valid Post post
            , BindingResult result
            , Long [] delfile // 삭제될 파일들
            , Model model
            , RedirectAttributes redirectAttrs
    ){
        // 만약 에러가 있다면 redirect 한다!
        if(result.hasErrors()){
            // addAttribute
            //    request parameters로 값을 전달.  redirect URL에 query string 으로 값이 담김
            //    request.getParameter에서 해당 값에 액세스 가능
            // addFlashAttribute
            //    일회성. 한번 사용하면 Redirect후 값이 소멸
            //    request parameters로 값을 전달하지않음
            //    '객체'로 값을 그대로 전달

            // redirect 시, 기존에 입력했던 값들은 보이게 하기
            // addFlashAttribute : 쿼리스트링으로 안가고 내부에서 세션으로 넘어감
            redirectAttrs.addFlashAttribute("subject", post.getSubject());
            redirectAttrs.addFlashAttribute("content", post.getContent());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/update/" + post.getId();
        }

        model.addAttribute("result", boardService.update(post, files, delfile));
        return "board/updateOk";
    }

    @PostMapping("/delete")
    public String deleteOk(Long id, Model model){
        model.addAttribute("result", boardService.deleteById(id));
        return "board/deleteOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        System.out.println("BoardController.initBinder() 호출");
        binder.setValidator(new PostValidator());
    }

    // 페이징
    // pageRows 변경시 동작
    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows){
        U.getSession().setAttribute("pageRows", pageRows);
        return "redirect:/board/list?page=" + page;
    }

}
