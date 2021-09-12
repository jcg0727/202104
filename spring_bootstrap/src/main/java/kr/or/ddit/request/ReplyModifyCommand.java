package kr.or.ddit.request;

import kr.or.ddit.dto.ReplyVO;

public class ReplyModifyCommand extends ReplyRegistCommand {

	private int rno;
	
	public int getRno() {
		return rno;
	}
	
	public void steRno(int rno) {
		this.rno = rno;
	}
	
	@Override
	public ReplyVO toReplyVO() {
		ReplyVO reply = super.toReplyVO();
		reply.setRno(this.rno);
		return reply;
	}
}
