package ps.zjl.demo;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest {
	// 教程地址： http://how2j.cn/k/lambda/lambda-lamdba-tutorials/697.html

	public static void main(String[] args) {
		List<Hero> heros = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Hero h = new Hero();
			h.hp = (int) Math.floor(Math.random() * 60 + 70);
			h.damage = (int) Math.floor(Math.random() * 60 + 20);
			System.out.println(h);
			heros.add(h);
		}
		System.out.println("------------");
		// 传统方法（匿名内部类）
		HeroChecker c = new HeroChecker() {
			@Override
			public boolean test(Hero hero) {
				return hero.hp > 100 && hero.damage < 50;
			}
		};
		filter(heros, c);

		// lambda(普通用法)
		filter(heros, h -> h.hp > 100 && h.damage < 50);
		// lambda(静态方法引用)
		filter(heros, h -> LambdaTest.testHero(h));
		// lambda(静态方法引用  升级)
		filter(heros, LambdaTest::testHero);
		// lambda(成员方法引用)
		LambdaTest test = new LambdaTest();
		filter(heros, test::testHeroPlus);
		// lambda(对象方法引用 )
		filter(heros, h -> h.matchs());
		// lambda(对象方法引用  升级)
		filter(heros, Hero::matchs);
		// 聚合操作用法（过滤、遍历）
		heros.stream().filter(h -> h.hp > 100 && h.damage < 50).forEach(h -> System.out.println(h));
		// 聚合操作用法（转换、遍历）
		heros.stream().mapToInt(h -> h.damage).forEach(h -> System.out.println(h));
		// 聚合操作用法（比较取极值）
		System.out.println(heros.stream().max((h1, h2) -> h1.damage - h2.damage).get());
	}

	private static void filter(List<Hero> heros, HeroChecker checker) {
		for (Hero hero : heros) {
			if (checker.test(hero)) {
				System.out.println(hero);
			}
		}
	}

	public static boolean testHero(Hero h) {
		return h.hp > 100 && h.damage < 50;
	}

	public boolean testHeroPlus(Hero h) {
		return h.hp > 100 && h.damage < 50;
	}
}

class Hero {
	int hp;
	int damage;

	public Hero() {
		super();
	}

	@Override
	public String toString() {
		return "Hero [hp=" + hp + ", damage=" + damage + "]";
	}

	public boolean matchs() {
		return this.hp > 100 && this.damage < 50;
	}
}
