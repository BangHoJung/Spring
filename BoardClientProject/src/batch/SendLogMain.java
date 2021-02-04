package batch;

public class SendLogMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SendLogCronTrigger trigger = new SendLogCronTrigger("0/5 * * * * ?", SendLogJob.class);
		trigger.triggerJob();
	}

}
