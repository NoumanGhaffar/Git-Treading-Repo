package com.example.seezassignment.network.response

/**
 * Response data from API calls.
 */
abstract class BaseResponse<T : Any> {

  /**
   * Response data can be converted into [T] via this method.
   */
  abstract fun toModel(): T

}