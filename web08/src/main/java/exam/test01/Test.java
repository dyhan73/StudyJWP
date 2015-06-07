package exam.test01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 1001235 on 15. 6. 7..
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test01/beans.xml");

        System.out.println("[컨테이너에 보관된 객체의 이름 출력]");
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        System.out.println("[score의 별명 출력]");
        for (String alias : ctx.getAliases("score2")) {
            System.out.println(alias);
        }

        Score score = (Score) ctx.getBean("score");
        Score score2 = (Score) ctx.getBean("score2");
        Score score3 = (Score) ctx.getBean("score3");
        Score score4 = (Score) ctx.getBean("score4");

        Score scoreOk = (Score) ctx.getBean("score-ok!");

        System.out.println("[빈 꺼내기]");
        if (score != null) System.out.println("score 찿음");
        if (score2 != null) System.out.println("score2 찿음");
        if (score3 != null) System.out.println("score3 찿음");
        if (score4 != null) System.out.println("score4 찿음");
        if (scoreOk != null) System.out.println("score-ok! 찿음");

        System.out.println("[생성된 빈 비교");
        if (score == score2) System.out.println("score == score2");
        if (score2 == score3) System.out.println("score2 == score3");
        if (score3 == score4) System.out.println("score3 == score4");
        if (score4 == scoreOk) System.out.println("score4 == scoreOk");

//        System.out.println("합계 : " + score.sum());
//        System.out.println("평균 : " + score.average());
    }
}