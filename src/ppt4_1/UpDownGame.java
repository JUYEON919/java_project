package ppt4_1;

import java.util.Random;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpDownGame{
	
	private int number = 0; // 유저가 맞추어야 할 난수(1~100)
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
	private final int MAX = 10;
	private int cnt = 0;
	
	private boolean isTimeout = false; //타임오버추가
	
	public UpDownGame() {
		
		Random rand = new Random();
		number = rand.nextInt(100) + 1; // 1 ~ 100 사이의 난수 발생
		startTime = LocalDateTime.now();
		System.out.println("지금부터 게임을 시작하지. \n1~100사이의 랜덤한 숫자를 맞추도록.");
		System.out.println("시작 시간 : " + startTime.format(formatter));
		
	}
	
	public boolean chkNum(int input) {
		cnt++;
		
		if(input == number) {
			endTime = LocalDateTime.now();
			System.out.println("정답입니다.");
			System.out.println("입력 횟수 : " + cnt + "번, 경과시간 " + Duration.between(startTime, endTime).getSeconds() + "초");
		} else if(cnt >= MAX) {
			System.out.println("탈락입니다.");
			System.out.println("정답은 " +number+ "(이)었습니다.");
			System.exit(0);
		} else if(input > number) {
			System.out.println("Down");
			return false;
		} else if(input < number) {
			System.out.println("Up");
			return false;
		}
		
		System.out.println("종료 시간 : " + endTime.format(formatter) );
		return true;
	}
	
	
	public void startTimeoutThread() {//타임오버추가한부분
	    Thread timeoutThread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                Thread.sleep(30000); // 30초 대기
	                if (!isTimeout) {
	                    System.out.println("TimeOver 게임 종료!");
	                    System.exit(0);
	                }
	            } catch (InterruptedException e) {
	                System.out.println("타임아웃 스레드가 중단되었습니다.");
	            }
	        }
	    });
	    timeoutThread.start();
	}

	public boolean isTimeout() {
	    return isTimeout;
	}


}

// scan.nextLine()
// System.currentTimeMillis()
// Integer.parseInt(userInput)