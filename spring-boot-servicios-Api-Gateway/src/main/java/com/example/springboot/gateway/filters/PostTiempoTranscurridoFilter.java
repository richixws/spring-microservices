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
public class PostTiempoTranscurridoFilter  extends ZuulFilter{

	
	private static final Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

	
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx=RequestContext.getCurrentContext();
		HttpServletRequest request=ctx.getRequest();
		
		log.info("entrando al filter");
		
		Long tiempoinicio=(Long) request.getAttribute("tiempoinicio");
		Long tiempofinal=System.currentTimeMillis();
		Long tiempoTrancurido=tiempofinal-tiempoinicio;
		
		log.info(String.format("tiempo transcurido en segundos %s  seg", tiempoTrancurido.doubleValue()/100.00));
		log.info(String.format("tiempo transcurido en milisegundis %s ms ", tiempoTrancurido));
		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	
	
}
