package com.example.fake_matrial.data.gateways

import retrofit2.Retrofit
import javax.inject.Inject


class ServerGatewayImplementer @Inject constructor(private val api: Retrofit) : ServerGateway {



}

interface ServerGateway {

}