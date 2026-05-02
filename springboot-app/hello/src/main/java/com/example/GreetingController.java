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
		String safeName = name.replaceAll("[<>&\"']", "");
		return """
			<!doctype html>
			<html lang="ko">
			<head>
			  <meta charset="utf-8">
			  <meta name="viewport" content="width=device-width, initial-scale=1">
			  <title>Hello, %s</title>
			  <script src="https://cdn.tailwindcss.com"></script>
			  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700;900&display=swap" rel="stylesheet">
			  <style>
			    body { font-family: 'Inter', system-ui, sans-serif; }
			    @keyframes float { 0%%,100%% { transform: translateY(0) } 50%% { transform: translateY(-12px) } }
			    @keyframes fadeUp { from { opacity:0; transform: translateY(20px) } to { opacity:1; transform: translateY(0) } }
			    .float { animation: float 4s ease-in-out infinite; }
			    .fade-up { animation: fadeUp 0.8s ease-out both; }
			    .delay-1 { animation-delay: 0.15s; }
			    .delay-2 { animation-delay: 0.3s; }
			    .blob { filter: blur(80px); opacity: 0.55; }
			  </style>
			</head>
			<body class="min-h-screen overflow-hidden bg-slate-950 text-white relative flex items-center justify-center p-6">
			  <div class="absolute -top-32 -left-32 w-96 h-96 rounded-full bg-indigo-500 blob"></div>
			  <div class="absolute -bottom-32 -right-32 w-96 h-96 rounded-full bg-pink-500 blob"></div>
			  <div class="absolute top-1/2 left-1/2 w-80 h-80 rounded-full bg-cyan-400 blob"></div>

			  <main class="relative z-10 w-full max-w-2xl">
			    <div class="bg-white/10 backdrop-blur-2xl border border-white/20 rounded-3xl shadow-2xl p-10 sm:p-14 text-center">
			      <div class="float text-7xl mb-6 fade-up">👋</div>
			      <h1 class="fade-up delay-1 text-5xl sm:text-6xl font-black tracking-tight mb-4
			                 bg-gradient-to-r from-white via-indigo-200 to-pink-200 bg-clip-text text-transparent">
			        Hello, %s!
			      </h1>
			      <p class="fade-up delay-2 text-white/70 text-lg mb-8">
			        Spring Boot · Running on Kubernetes ☁️
			      </p>
			      <div class="fade-up delay-2 flex flex-wrap justify-center gap-3 text-sm">
			        <a href="/hello?name=%s" class="px-5 py-2.5 rounded-full bg-white text-slate-900 font-semibold hover:bg-indigo-100 transition">
			          /hello JSON →
			        </a>
			        <a href="/?name=Claude" class="px-5 py-2.5 rounded-full bg-white/10 border border-white/30 hover:bg-white/20 transition">
			          이름 바꿔보기
			        </a>
			      </div>
			    </div>
			    <p class="text-center text-white/40 text-xs mt-6">tip: <code class="text-white/60">?name=YourName</code> 쿼리로 인사받기</p>
			  </main>
			</body>
			</html>
			""".formatted(safeName, safeName, safeName);
	}
}