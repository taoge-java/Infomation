package com.information.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
/**
 * velocity模板标签基类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月11日 下午7:08:02
 */
public abstract class BaseDirective extends Directive{

	@Override
	public String getName() {
		return null;
	}

	@Override
	public int getType() {
		return 0;
	}

	@Override
	public boolean render(InternalContextAdapter adapter, Writer writer, Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		
		return false;
	}

}
