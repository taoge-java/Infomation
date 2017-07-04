package com.information.dao.weixin.message.response;

import com.information.dao.weixin.base.BaseMessae;
import com.information.dao.weixin.message.Voice;

/**
 * 回复语音消息
 * @author zengjintao
 * @version 1.0 
 * 2017年4月8日9:21
 */
public class ResponseVoiceMessage extends BaseMessae{
	
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice Voice) {
		this.Voice = Voice;
	}

}
