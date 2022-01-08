package com.example.warehouse.di

import com.example.warehouse.customers.data.repo.CustomerRepoImpl
import com.example.warehouse.customers.data.source.CustomerRetrofitService
import com.example.warehouse.customers.domain.repo.CustomerRepo
import com.example.warehouse.customers.domain.use_case.*
import com.example.warehouse.orders.data.repo.OrderRepoImpl
import com.example.warehouse.orders.data.source.OrderRetrofitService
import com.example.warehouse.orders.domain.repo.OrderRepo
import com.example.warehouse.orders.domain.use_case.*
import com.example.warehouse.products.data.repo.ProductRepoImpl
import com.example.warehouse.products.data.source.ProductRetrofitService
import com.example.warehouse.products.domain.repo.ProductRepo
import com.example.warehouse.products.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "http://192.168.178.25:3000/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProductRepo(): ProductRepo {
        return ProductRepoImpl(
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductRetrofitService::class.java)
        )
    }

    @Singleton
    @Provides
    fun provideProductUseCases(repo: ProductRepo): ProductUseCases {
        return ProductUseCases(
            GetProductsUseCase(repo),
            GetProductUseCase(repo),
            DeleteProductUseCase(repo),
            UpdateProductUseCase(repo),
            CreateProductUseCase(repo)
        )
    }

    @Singleton
    @Provides
    fun provideCustomerRepo(): CustomerRepo {
        return CustomerRepoImpl(
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CustomerRetrofitService::class.java)
        )
    }

    @Singleton
    @Provides
    fun provideCustomerUseCases(repo: CustomerRepo): CustomerUseCases {
        return CustomerUseCases(
            GetCustomersUseCase(repo),
            GetCustomerUseCase(repo),
            DeleteCustomerUseCase(repo),
            UpdateCustomerUseCase(repo),
            CreateCustomerUseCase(repo)
        )
    }

    @Singleton
    @Provides
    fun provideOrderRepo(): OrderRepo {
        return OrderRepoImpl(
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OrderRetrofitService::class.java)
        )
    }

    @Singleton
    @Provides
    fun provideOrderUseCases(repo: OrderRepo): OrderUseCases {
        return OrderUseCases(
            GetOrderPreviewsUseCase(repo),
            UpdateOrderCompletedUseCase(repo),
            GetOrderDetailsUseCase(repo),
            CreateOrderUseCase(repo)
        )
    }

}