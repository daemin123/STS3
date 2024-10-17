# TRANSACTION

|-|
|-|
|[바로가기](https://velog.io/@shasha/Database-%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EC%A0%95%EB%A6%AC)|

개념
---
> TRANSCTION 정의<br>

```
 DB의 상태를 변경시키는 작업의 단위
```

>ACID<br>

```
Atomicity (원자성)
트랜잭션이 DB에 모두 반영되거나, 혹은 전혀 반영되지 않아야 된다 (All or Nothing).

Consistenty (일관성)
트랜잭션의 작업 처리 결과는 항상 일관성 있어야 한다.

시스템이 가지고 있는 고정 요소는 트랜잭션 수행 전과 수행 후의 상태가 같아야 한다는 말로,

DB의 제약조건을 위배하는 작업을 트랜잭션 과정에서 수행할 수 없음을 나타낸다.

ex) 송금 시 금액의 데이터 타입을 정수형(integer)에서 문자열(string)로 변경할 수 없음.

Isolation (독립성)
둘 이상의 트랜잭션이 동시에 병행 실행되고 있을 때, 어떤 트랜잭션도 다른 트랜잭션 연산에 끼어들 수 없다.

Durability (지속성)
트랜잭션이 성공적으로 완료되었으면, 결과는 영구적으로 반영되어야 한다.

```

> TRANSACTION 연산<br>
```
(1) COMMIT 연산
트랜잭션이 성공적으로 수행되었음을 선언하는 연산으로,

COMMIT ****연산의 실행을 통해 트랜잭션의 수행이 성공적으로 완료되었음을 선언하고, 그 결과를 최종 DB에 반영한다.

(2) ROLLBACK 연산
트랜잭션 수행이 실패했음을 선언하고 작업을 취소하는 연산으로,

트랜잭션이 수행되는 도중 일부 연산이 처리되지 못한 상황이라면 ROLLBACK 연산을 실행하여 트랜잭션 수행이 실패했음을 선언하고, DB를 트랜잭션 수행 전과 일관된 상태로 되돌려야 한다.
```


> TRANSACTION 상태<br>

```
Active
트랜잭션 활동 상태

트랜잭션이 실행 중이며 동작 중인 상태를 말한다.

Partially Committed
트랜잭션의 COMMIT 명령이 도착한 상태

트랜잭션의 COMMIT 이전 SQL문이 수행되고, COMMIT 만 남은 상태를 말한다.

(트랜잭션의 마지막 연산까지 실행하고 COMMIT 연산을 실행하기 직전의 상태)

Failed
트랜잭션 실패 상태

더이상 트랜잭션이 정상적으로 진행될 수 없는 상태를 말한다.

Committed
트랜잭션 완료 상태

트랜잭션이 정상적으로 완료된 상태를 말한다.

Aborted
트랜잭션 취소 상태

트랜잭션이 취소되고, 트랜잭션 실행 이전 데이터로 돌아간 상태를 말한다.
(트랜잭션 수행을 실패하고 ROLLBACK 연산을 실행한 상태)

```

실습
---
> pom.xml <br>

```
		<!-- Spring - Tx -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-tx</artifactId>
		    <version>5.0.7.RELEASE</version>
		</dependency>
```

> TxConfig.java <br>

```
@Configuration
@EnableTransactionManagement
public class TxConfig {
	
	@Autowired
	private DataSource dataSource3;
	
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource3);
    }
}


@EnableTransactionManagement  트랜잭션 관리를 활성화
                              이 어노테이션을 사용하면 스프링은 @Transactional 어노테이션이 붙은 메서드나 클래스의 트랜잭션을 자동으로 관리
                              @EnableTransactionManagement는 내부적으로 AOP(Aspect-Oriented Programming)을 사용하여 트랜잭션 관련 기능을 구현
```

> MemoService <br>

```
package com.example.app.domain.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.common.dto.MemoDto;
import com.example.app.domain.common.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemoService {
	
	
	@Autowired
	private MemoMapper mapper;
	
	//전체메모가져오기
	public List<MemoDto> getAllMemo(){
		log.info("MemoService's getAllMemo Call! ");
		return  mapper.selectAll(); 
	}
	//메모작성
	public void addMemo(MemoDto dto) {
		log.info("MemoService's addMemo Call! ");
		mapper.insert(dto);
	}	
	
	//메모작성 
	@Transactional(rollbackFor = Exception.class) 
	public void addMemoTx(MemoDto dto)	 {
		log.info("MemoService's addMemoTx Call! ");
		int id=dto.getId();
		mapper.insert(dto);	//01 정상INSERT 
		dto.setId(id);		//PK오류 발생예정인 dto
		mapper.insert(dto);	//02	PK오류 발생!!		
	}		
}

```


@Transactionl Option 정리
---
> <br>

```
propagation: 트랜잭션 전파 방식을 설정합니다. 다른 트랜잭션 내에서 메서드가 호출될 때 어떻게 트랜잭션을 전파할지를 결정합니다.
예를 들어, REQUIRED, REQUIRES_NEW, SUPPORTS 등이 있습니다.

isolation: 트랜잭션 격리 수준을 설정합니다. 여러 트랜잭션이 동시에 실행될 때 데이터의 일관성을 보장하기 위해 어떤 수준의 격리를 유지할지를 결정합니다.
예를 들어, DEFAULT, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE 등이 있습니다.

timeout: 트랜잭션 타임아웃을 설정합니다. 특정 시간 내에 트랜잭션이 완료되지 않으면 롤백됩니다. 단위는 초(seconds)입니다.

readOnly: 읽기 전용 트랜잭션 여부를 설정합니다. 읽기 전용 트랜잭션은 해당 트랜잭션 동안 데이터를 수정하지 않음을 나타냅니다. 기본값은 false이며, true로 설정하면 성능을 향상시킬 수 있습니다.

rollbackFor / noRollbackFor: 롤백을 수행할 예외 클래스를 지정하거나 롤백을 수행하지 않을 예외 클래스를 지정합니다.
```
