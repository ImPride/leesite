package com.funtl.leesite.common.sms.consumer;

import com.funtl.leesite.common.sms.publisher.SmsValidateEvent;
import com.funtl.leesite.common.utils.CacheUtils;
import com.lmax.disruptor.EventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信验证码消费者，从缓存中删除验证码
 * Created by Lusifer on 2017/5/5.
 */
public class SmsValidateEventRemoveCacheHandler implements EventHandler<SmsValidateEvent> {
	private static final Logger logger = LoggerFactory.getLogger(SmsValidateEventRemoveCacheHandler.class);

	@Override
	public void onEvent(SmsValidateEvent smsValidateEvent, long sequence, boolean endOfBatch) throws Exception {
		String phoneNumber = smsValidateEvent.getPhoneNumber();
		String code = smsValidateEvent.getCode();

		logger.debug("短信验证码消费者：手机号（{}），验证码（{}）", phoneNumber, code);
		CacheUtils.remove("smsCache", phoneNumber);
	}
}
