package com.example.vr.web;

import com.example.vr.bean.Response;
import com.example.vr.bean.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@CrossOrigin
@RestController
@RequestMapping("/music")
public class MusicController {

    @GetMapping("/play")
    public Response<Boolean> play(@RequestParam String name, HttpServletRequest request){
        Object username = request.getSession().getAttribute("username");
        if(username == null) {
            return new Response<>("ERROR", "暂未登录", false);
        }
        User user = (User) request.getSession().getAttribute("user");
        if(user.getAdmin() != 1) {
            return new Response<>("ERROR", "您不是管理员身份，无法进行播放操作",false);
        }
        String cmd = "omxplayer -o local " + name;
        linuxcmd(cmd);
        return new Response<>("OK", "播放成功", true);
    }

    @PostMapping("/upload")
    public Response<Boolean> upload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        Object username = request.getSession().getAttribute("username");
        if(username == null) {
            return new Response<>("ERROR", "暂未登录", false);
        }
        if (file.isEmpty()){
            return new Response<Boolean>("ERROR", "上传文件为空", false);
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/home/pi/Documents/music/";
        File dest = new File(filePath + fileName);
        try{
            file.transferTo(dest);
            return new Response<Boolean>("OK", "上传文件成功", true);

        }catch (IOException e){
            e.printStackTrace();
            return new Response<Boolean>("ERROR", "上传错误", false);
        }
    }
    public void linuxcmd(String cmd) {
        System.out.println("got cmd job : " + cmd);
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec(cmd);
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            // System.out.println("[check] now size \n"+bs.readLine());
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[8192];
            for (int n; (n = in.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
            System.out.println("job result [" + out.toString() + "]");
            in.close();
            // process.waitFor();
            process.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
