package com.example.warehouse.orders.data.source

import com.example.warehouse.orders.domain.model.NewOrder
import com.example.warehouse.orders.domain.model.OrderDetails
import com.example.warehouse.orders.domain.model.OrderPreview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrderRetrofitService {

    @GET("apiv1/orderPreviews")
    suspend fun getOrderPreviews(): Response<List<OrderPreview>>

    @GET("apiv1/orderDetails/{orderId}")
    suspend fun getOrderDetails(@Path("orderId") orderId: Int): Response<List<OrderDetails>>

    @PUT("apiv1/orderCompletion/{orderId}/{newValue}")
    suspend fun updateOrderCompletedValue(
        @Path("orderId") orderId: Int,
        @Path("newValue") newValue: Int
    )

    @POST("apiv1/orders")
    suspend fun createOrder(newOrder: NewOrder)

}