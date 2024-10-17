# INIT


@ExceptionHandler
---
> 특정 컨트롤러 내에서 예외 처리를 담당<br>

> 예외가 발생했을 때 특정한 메서드가 호출되어 예외를 처리하고, 사용자에게 적절한 응답을 반환 <br>


MemoController
---

```
@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	
	@ExceptionHandler(FileNotFoundException.class)
	public String fileNotFoundExceptionHandler(Exception error,Model model) {
		log.info("MemoController's fileNotFoundExceptionHandler...error " + error);
		model.addAttribute("error",error);
		return "memo/error";
	}
	
 ....

	@GetMapping("/ex_test")
	public void ex_test() throws FileNotFoundException{
		System.out.println("GET /member/list1");
		throw new FileNotFoundException("파일이 없다..");
	}
	
}
```

@ControllerAdvice
---
>  여러 컨트롤러 클래스에서 발생하는 예외를 한 곳에서 처리할 수 있도록 도와주는 스프링 프레임워크의 기능 <br>

```
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(FileNotFoundException.class)
	public String error1(Exception ex,Model model) {
		System.out.println("GlobalExceptionHandler FileNotFoundException... ex " + ex);
		System.out.println("GlobalExceptionHandler FileNotFoundException... ex ");
		model.addAttribute("error",ex);
		return "memo/error";
	}
	@ExceptionHandler(ArithmeticException.class)
	public String error2(Exception ex,Model model) {
		System.out.println("GlobalExceptionHandler ArithmeticException... ex " + ex);
		System.out.println("GlobalExceptionHandler ArithmeticException... ex ");
		model.addAttribute("error",ex);
		return "memo/error";
	}	
	@ExceptionHandler(Exception.class)
	public String error3(Exception ex,Model model) {
		System.out.println("GlobalExceptionHandler Exception... ex " + ex);
		System.out.println("GlobalExceptionHandler Exception... ex ");
		model.addAttribute("error",ex);
		return "memo/error";
	}
}

```
