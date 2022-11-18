package com.yangbong.core_ui.extension

import androidx.appcompat.widget.AppCompatEditText
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * AppCompatEditText에 쿼리 디바운싱 적용하는 메소드
 * 자주 사용하므로 확장함수로 분리하였습니다.
 *
 * @author onseok
 * @param queryFunction
 * @return Disposable 반환
 */
fun AppCompatEditText.setQueryDebounce(queryFunction: (String) -> Unit): Disposable {
    val editTextChangeObservable = this.textChanges()
    val editTextSubscription: Disposable =
        editTextChangeObservable
            // 마지막 글자 입력 timeout(1) 초 후에 onNext 이벤트로 데이터 발행
            .debounce(1000L, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            // 구독을 통해 이벤트 응답 처리
            .subscribeBy(
                onNext = {
                    Timber.d("onNext : $it")
                    queryFunction(it.toString().trim())
                },
                onComplete = {
                    Timber.d("onComplete")
                },
                onError = {
                    Timber.d("onError : $it")
                }
            )
    return editTextSubscription
}