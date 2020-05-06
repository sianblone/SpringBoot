# Spring Boot에서 프로젝트가 시작될 때 실행할 행동 작성하기
1. 아무 클래스에 public CommandLineRunner형 메소드를 @Bean으로 설정하고 return new CommandListRunner() { public void run(String... args) throws Exception { } } 안쪽에 설정하기
2. 새로운 클래스를 만들어서 클래스를 @Bean(@Component)을 설정해주고 public class AppInit implements CommandLineRunner { public void run(String... args) throws Exception { } } 안쪽에 설정하기
* 실행하지 않으려면 @Bean만 제거하면 된다