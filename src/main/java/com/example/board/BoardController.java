package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping(value="/board")
public class BoardController {
    @Autowired
    BoardService boardService;

//    @RequestMapping(value="/")
//    public String home(){
//        return "posts";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String boardlist(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        return "posts";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPost() {
        return "addpostform";
    }

    @RequestMapping(value = "/addok", method = RequestMethod.POST)
    public String addPostOK(BoardVO vo) {
        int i = boardService.insertBoard(vo);
        if(i == 0)
            System.out.println("데이터 추가 실패");
        else
            System.out.println("데이터 추가 성공!!!");
        return "redirect:/";
    }

    @RequestMapping(value = "/editform/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") int id, Model model) {
        BoardVO boardVO = boardService.getBoard(id);
        model.addAttribute("u", boardVO);
        return "editform";
    }
    @RequestMapping(value = "/editok", method = RequestMethod.POST)
    public String editPostOk(BoardVO vo) {
        int i = boardService.updateBoard(vo);
        if(i == 0)
            System.out.println("데이터 수정 실패 ");
        else
            System.out.println("데이터 수정 성공!!!");
        return "redirect:/";
    }

    @RequestMapping(value = "/deleteok/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") int id) {
        int i = boardService.deleteBoard(id);
        if(i == 0)
            System.out.println("데이터 삭제 실패 ");
        else
            System.out.println("데이터 삭제 성공!!!");
        return "redirect:../";
    }
}
