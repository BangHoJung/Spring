package main;

public class StartSendLog {
	public static void main(String[] args) {
		SendLogCronTrigger trigger = new SendLogCronTrigger("0 0 0 1/1 * ? *", SendLogJob.class);
//		SendLogCronTrigger trigger = new SendLogCronTrigger("0/5 * * * * ? *", SendLogJob.class);
		trigger.triggerJob();
	}
}
