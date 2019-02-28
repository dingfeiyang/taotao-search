package com.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/query")
	@ResponseBody
	public TaotaoResult seacher(@RequestParam("q")String queryString,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="60",required=false)Integer rows){
		if(StringUtils.isBlank(queryString)){
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		SearchResult seacher = new SearchResult();
		try{
			queryString = new String(queryString.getBytes("ISO8859-1"),"UTF-8");
			seacher = searchService.seacher(queryString, page, rows);	
		}catch(Exception e){
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(seacher);
		
	}
}
