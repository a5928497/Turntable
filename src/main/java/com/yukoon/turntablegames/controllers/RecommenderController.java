package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Recommender;
import com.yukoon.turntablegames.services.RecommenderService;
import jdk.nashorn.internal.ir.CatchNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RecommenderController {

    @Autowired
    RecommenderService recommenderService;

    @GetMapping("/recommenders/{id}")
    public String findAll(@PathVariable("id")Integer act_id, Map<String,Object> map) {
        map.put("recommenders",recommenderService.findAll(act_id));
        map.put("act_id",act_id);
        return "background/recommender_list";
    }

    @DeleteMapping("/recommender/{id}")
    public String delrecommender(@PathVariable("id")Integer id,Integer act_id) {
        recommenderService.delRecommender(id);
        return "redirect:/recommenders/" + act_id;
    }

    @GetMapping("/recommenderadd/{act_id}")
    public String toAdd(@PathVariable("act_id")Integer act_id,Map<String,Object> map) {
        map.put("act_id", act_id);
        return "background/recommender_input";
    }

    @GetMapping("/recommender/{id}")
    public String toEdit(@PathVariable("id")Integer id,Map<String,Object> map) {
        Recommender recommender = recommenderService.findById(id);
        map.put("recommender",recommender);
        return "background/recommender_input";
    }

    @PostMapping("/recommender")
    public String add(Recommender recommender,Map<String,Object> map) {
        recommenderService.addRecommender(recommender);
        map.put("act_id",recommender.getAct_id());
        return "redirect:/recommenders/"+recommender.getAct_id();
    }
}
