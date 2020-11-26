package me.frenchline.corewebmvc;

/**
 * 커스텀한 예외를 정의
 * - RuntimeException 를 상속받는 unchecked exception을 만듦
 * - unchecked exception이므로 메서드 시그니처를 변경하거나 try/catch 할 필요없이 던지기만 하면 된다
 * - 만약 이 에러가 발생하면 특정한 메세지와 함께 특정한 에러 페이지를 보여주자
 */
public class EventException extends RuntimeException {
}
