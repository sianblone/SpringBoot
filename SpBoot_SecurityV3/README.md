# Spring Boot에서 프로젝트가 시작될 때 실행할 행동 작성하기
1. 아무 클래스에 public CommandLineRunner형 메소드를 @Bean으로 설정하고 return new CommandListRunner() { public void run(String... args) throws Exception { } } 안쪽에 설정하기
2. 새로운 클래스를 만들어서 클래스를 @Bean(@Component)을 설정해주고 public class AppInit implements CommandLineRunner { public void run(String... args) throws Exception { } } 안쪽에 설정하기
* 실행하지 않으려면 @Bean만 제거하면 된다

# 연동하기 위한 기본 설정
 * Spring Security는 연동 정책이 자체의 view template를 사용한다는 전제하에 모든 프로젝트가 진행된다.
 * 
 * 1. form(입력)을 열기 위한 URL을 열면 Spring Security는 csrf token을 함께 전송해서 POST로 값을 보낼때 검증하는 값을 보낸다.
 * jsp, thymeleaf에는 csrf token을 수신하여 input tag로 설정하는 기능이 있다.
 * submit을 했을 때 input hidden 등으로 csrf token을 함께 전송해서 security가 올바른 요청인지 검증하는 절차가 수행된다.
 * 
 * react, angular같은 별도의 view를 사용하여 연동할 때는 csrf를 처리할 수 있는 방법이 거의 없다.
 * 따라서 react 등을 사용할 때는 csrf token을 검증하는 절차를 생략하고 사용할 수 밖에 없기 때문에 HttpSecurity.csrf().disable()을 적용해야 한다.
 * 이 설정을 적용하면 csrf값을 받아올 수 없기 때문에 jsp, thymeleaf에서 값을 받는 절차에서 오류가 발생하고 정상적인 view가 보이지 않게 된다
 * 
 * 2. URL을 localhost:8080/admin과 같이 요청할 때 접근할 수 있는 ROLE을 설정해두면
 * login이 되어있지 않은 경우(ROLE이 없는 경우) 미리 지정한 login form template이 보여지게 된다.
 * 이런 경우 react 등에서는 서버에 어떤 반응이 있는지 전혀 인지할 수 없다.
 * 따라서 .httpBasic().disable() 을 적용해서 사용하지 않아야 한다.
 * 
 * 3. 기본 template를 사용할 때 jsp의 <sec:>, <authority:> 같은 taglib, thymeleaf의 <th sec:> 등에서 로그인한 사용자 정보를 조회하는 코드가 있는데
 * 이 코드들은 실제로 security와 연동된 HttpSession을 사용한다
 * 이 기능도 react 등을 연동할 때는 사용하지 않도록 설정해야 한다
 * 
 * 또한 spring과 react 등이 연동될 때는 jwt 보안기능을 사용한다.
 * 이 jwt 보안기능이 HttpSession을 사용하게 되면 http 프로토콜 레벨에서 문제를 일으킬 수 있고
 * 오히려 jwt의 보안기능이 약화되는 현상을 보이게 된다
 * 
 * 따라서 react 등과 연동할 때는 session을 사용하지 않도록 설정한다
 * session을 사용하지 않으면 기본 template에서는 사용자의 로그인 상태, ROLE 상태 등을 인지할 수 있는 방법이 없다.
 * react와 기본 template를 연동할 때는 session 기능을 열어두고 react에서는 보안과 관련된 접근을 금지하고 보안이 필요없는 부분만 접근하도록 해야한다.