package coeait.g333.orlov.lab07apicalculator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CalculatorService
{
    @GET("calculator")
    Call <String> calculator(@Query("a") String a, @Query("b") String  b, @Query("oper") String oper);
}