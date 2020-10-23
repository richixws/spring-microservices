package com.example.springboot.gateway.filters;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter  extends ZuulFilter{

	
	private static final Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

	
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx=RequestContext.getCurrentContext();
		HttpServletRequest request=ctx.getRequest();
		
		log.info(String.format("%s request enrutado a %s", request.getMethod(),request.getRequestURI().toString()));
		
		Long tiempoinicio=System.currentTimeMillis();
		request.setAttribute("tiempoinicio", tiempoinicio);
		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	
	
}
