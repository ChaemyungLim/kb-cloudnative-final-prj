package com.example;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/hello")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping(value = "/", produces = "text/html;charset=UTF-8")
	public String home(@RequestParam(value = "name", defaultValue = "World") String name) {
		String safeName = name.replaceAll("[<>&\"'%]", "");
		String[] greetings = {
			"hewwo, {name}~ uwu",
			"H3LL0... wait wrong button",
			"meow?? oh hi {name}",
			"asdkfjasdf...zzz",
			"{name}?? 밥 줄거야??",
			"...zzz... (꿈에서 {name}를 봤다옹)",
			"i wuz sleeping. now i am not. thx {name}.",
			"purrr~ {name} 왔구냥"
		};
		String pick = greetings[(int) (counter.incrementAndGet() % greetings.length)];
		String greeting = pick.replace("{name}", safeName);
		return """
			<!doctype html>
			<html lang="ko">
			<head>
			  <meta charset="utf-8">
			  <meta name="viewport" content="width=device-width, initial-scale=1">
			  <title>🐱 hewwo</title>
			  <style>
			    body {
			      margin: 0; min-height: 100vh;
			      background: linear-gradient(135deg, #ffe4ec 0%%, #fff5d6 100%%);
			      font-family: 'Comic Sans MS', system-ui, sans-serif;
			      display: flex; align-items: center; justify-content: center;
			      cursor: pointer;
			    }
			    .card {
			      text-align: center; padding: 3rem 2rem;
			    }
			    .cat {
			      font-size: 9rem; display: inline-block;
			      transition: transform 0.3s;
			      animation: snore 3s ease-in-out infinite;
			    }
			    body:hover .cat {
			      animation: none;
			      transform: scale(1.15) rotate(-8deg);
			    }
			    @keyframes snore {
			      0%%, 100%% { transform: rotate(-3deg) scale(1); }
			      50%%      { transform: rotate(3deg) scale(1.03); }
			    }
			    .zzz {
			      font-size: 1.8rem; color: #aaa;
			      position: relative; top: -3rem; left: 2rem;
			      animation: drift 3s ease-in-out infinite;
			    }
			    body:hover .zzz { opacity: 0; }
			    @keyframes drift {
			      0%%   { transform: translate(0,0); opacity: 0.3; }
			      50%%  { transform: translate(10px,-15px); opacity: 1; }
			      100%% { transform: translate(20px,-30px); opacity: 0; }
			    }
			    h1 {
			      font-size: 2.4rem; color: #5a3d5c;
			      margin: 0.5rem 0; font-weight: 900;
			    }
			    .hint {
			      color: #b08aa8; font-size: 0.9rem; margin-top: 1rem;
			    }
			  </style>
			</head>
			<body title="쓰담쓰담">
			  <div class="card">
			    <div>
			      <span class="cat">🐱</span><span class="zzz">💤</span>
			    </div>
			    <h1>%s</h1>
			    <p class="hint">(마우스 올려보면 깨어남 🐾)</p>
			  </div>
			</body>
			</html>
			""".formatted(greeting);
	}
}