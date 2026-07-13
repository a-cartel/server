// package server;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/Status")
// public class Status {

// 	@GetMapping("/Check")
// 	public String Check() {
// 		return "체크";
// 	}

// 	// 메모리 return(반환) 해주는 코드 작성할 것
// 	@GetMapping("/Memory")
// 	public String Memory() {
// 		Runtime rt = Runtime.getRuntime();

// 		long totalMemory = rt.totalMemory() / 1024 / 1024;
// 		long freeMemory = rt.freeMemory() / 1024 / 1024;
// 		long usedMemory = totalMemory - freeMemory;
// 		long maxMemory = rt.maxMemory() / 1024 / 1024;

// 		return "Total: " + totalMemory + "MB" + " | Used: " + usedMemory + "MB" + " | Free: " + freeMemory + "MB"
// 				+ " | Max: " + maxMemory + "MB";

// 	}

// }