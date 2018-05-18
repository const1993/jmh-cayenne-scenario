# Test framework for cayenne

#### Prerequisites
 * Java 1.8 or newer.
 * Apache Maven.

#### Download and build stub driver:
 ```
 https://github.com/const1993/stub-bootique-jdbc-driver.git
 cd stub-bootique-jdbc-driver
 mvn clean install
 ```

#### To run test on stub driver:
```
mvn test -DtestConnection=stub

```

To run with mysql
```
 mvn verify -DtestConnection=docker
```

Test results

|Benchmark                                                           |(driver)           |(isDefault)  |(password)                          |(url)  		|(username) |mode    |Cnt       |Score           |Error 	 |Units| 
|---|---|---|---|---|---|---|---|---|---|---|
|TestInsert.test_insert_batch1000x         			|io.bootique.jdbc.driver.stub.Driver       |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt |   5|    80294.532 	  |±     441.512  |us/op |
|TestInsert.test_insert_batch10x          			|io.bootique.jdbc.driver.stub.Driver       |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt |   5|   	796.069  |±      35.030 | us/op |
|TestInsert.test_insert_one               			|io.bootique.jdbc.driver.stub.Driver      |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt |   5|      291.796   |±     506.513  |us/op|
|TestRelatedInsert.test_insert_batch1000x  			|io.bootique.jdbc.driver.stub.Driver       |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt |   5|   143805.998   |±    1805.818  |us/op|
|TestRelatedInsert.test_insert_batch10x   			|io.bootique.jdbc.driver.stub.Driver       |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt |   5|     1838.317   |±     117.503  |us/op|
|TestRelatedInsert.test_insert_one       		    |io.bootique.jdbc.driver.stub.Driver       |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt |   5|      590.969   |±      54.576  |us/op|


|Benchmark                                                                     |(driver)  |(isDefault)  |(password)                                    |(url)  |(username)    |mode  |Cnt    		|Score   	|Error    |Units|
|---|---|---|---|---|---|---|---|---|---|---|
|TestPrefetchSelect.test_select_prefetch_batch1000x  |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|      3841.065   |±     737.324  |us/op|
|TestPrefetchSelect.test_select_prefetch_batch100x   |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|  	    483.855   |±     124.574  |us/op|
|TestPrefetchSelect.test_select_prefetch_batch10x    |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|        82.276   |±       4.316  |us/op|
|TestPrefetchSelect.test_select_prefetch_one         |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|        58.013   |±      64.014  |us/op|
|TestSimpleSelect.select_batch1000x                  |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|       909.426   |±      84.865  |us/op|
|TestSimpleSelect.select_batch100x                   |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|       111.188	  |±       6.963  |us/op|
|TestSimpleSelect.select_batch10x                    |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|        25.918   |±       1.241  |us/op|
|TestSimpleSelect.select_one                         |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|        11.085   |±       0.490  |us/op|
|TestWhereSelect.test_select_where_batch1000x        |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|      1400.372   |±    3364.057  |us/op|
|TestWhereSelect.test_select_where_batch100x         |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|       119.885   |±       8.036  |us/op|
|TestWhereSelect.test_select_where_batch10x          |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|        33.752   |±      11.408  |us/op|
|TestWhereSelect.test_select_where_one               |io.bootique.jdbc.driver.stub.Driver         |true    |password  |jdbc:stub://localhost:9999/cayenne_demo        |root  |avgt     |5|        16.093   |±       0.868  |us/op|

|Benchmark                                             |(driver)  |(isDefault)  |(password)                                                   |(url)  			|(username) 	 |mode  |Cnt         |Score         |Error    |Units|
|---|---|---|---|---|---|---|---|---|---|---|
|TestInsert.test_insert_batch1000x         			|com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32904/cayenne_demo?useSSL=false        |root  |avgt    | 5|  10287530.636   |±   301288.769  |us/op|
|TestInsert.test_insert_batch10x          			|com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32904/cayenne_demo?useSSL=false        |root  |avgt    | 5|    139245.270   |±    27320.533  |us/op|
|TestInsert.test_insert_one               			|com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32904/cayenne_demo?useSSL=false        |root  |avgt    |5 |    44049.547   |±     7366.484  |us/op|
|TestRelatedInsert.test_insert_batch1000x  			|com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32904/cayenne_demo?useSSL=false        |root  |avgt    |5 | 12239560.929   |± 18155792.804  |us/op|
|TestRelatedInsert.test_insert_batch10x   			|com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32904/cayenne_demo?useSSL=false        |root  |avgt    |5 |   177634.131   |±    39724.013  |us/op|
|TestRelatedInsert.test_insert_one                   |com.mysql.jdbc.Driver        |false   |           |jdbc:mysql://localhost:32904/cayenne_demo?useSSL=false        |root  |avgt   | 5|     84017.822   |±    40735.283  |us/op|

|Benchmark                                                       |(driver)  |(isDefault)  |(password)                                                   |(url)  |(username)  |mode  |Cnt     |Score      |Error  |Units|
|---|---|---|---|---|---|---|---|---|---|---|
|TestPrefetchSelect.test_select_prefetch_batch1000x  |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     8053.594   |±      748.576  |us/op|
|TestPrefetchSelect.test_select_prefetch_batch100x   |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     3694.076   |±     1447.249  |us/op|
|TestPrefetchSelect.test_select_prefetch_batch10x    |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     3683.320   |±     2285.391  |us/op|
|TestPrefetchSelect.test_select_prefetch_one         |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     3759.691   |±      754.099  |us/op|
|TestSimpleSelect.select_batch1000x                  |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     4550.974   |±      539.038  |us/op|
|TestSimpleSelect.select_batch100x                   |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     2919.481   |±      791.490  |us/op|
|TestSimpleSelect.select_batch10x                    |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     2142.071   |±      593.054  |us/op|
|TestSimpleSelect.select_one                         |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     2114.755   |±      228.145  |us/op|
|TestWhereSelect.test_select_where_batch1000x        |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     4612.711   |±      367.092  |us/op|
|TestWhereSelect.test_select_where_batch100x         |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     2346.412   |±      179.203  |us/op|
|TestWhereSelect.test_select_where_batch10x          |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     2315.878   |±      592.112  |us/op|
|TestWhereSelect.test_select_where_one               |com.mysql.jdbc.Driver        |false    |          |jdbc:mysql://localhost:32768/cayenne_demo?useSSL=false        |root  |avgt   |5|     2033.609   |±       96.319  |us/op|
