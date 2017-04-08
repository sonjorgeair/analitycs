package com.alpha.predictor.collector;

import com.alpha.predictor.collector.datasources.webpage.BasicWebpageDataSource;
import com.alpha.predictor.collector.datasources.webpage.WebPageDataSource;
import com.alpha.predictor.collector.service.DataService;
import com.alpha.predictor.collector.service.ServiceStatus;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectorApplicationTests {


	//@Rule
	//public WireMockRule wireMockRule = new WireMockRule(8089);

	@Autowired
	private DataService dataService;

	@Test
	public void contextLoads() {
	}



	@Test
	public void testDatasource()
	{
		WebPageDataSource dataSource = new BasicWebpageDataSource.Builder().url("http://www.mindicador.cl/api/uf").build();

		Assert.assertTrue(dataSource!= null);
		Assert.assertTrue(dataSource.parse() != null);
		Assert.assertTrue(dataSource.parse().isResponseAvailable());
	}



	@Test
	public void collectorServiceTest()
	{

		dataService.poolDatasources();

		ServiceStatus status = dataService.getStatus();

		Assert.assertTrue(status!= null);
		Assert.assertTrue(dataService.getDatasources().size()== 3);
		Assert.assertTrue(status.getNumberOfRuns()>=0);

	}

	private void mockResponses()
	{
		stubFor(get(urlEqualTo("/indicador/uf"))
						.willReturn(aResponse()
											.withStatus(200)
											.withBody("{\"version\":\"1.4.0\",\"autor\":\"mindicador.cl\",\"codigo\":\"uf\",\"nombre\":\"Unidad de fomento (UF)\",\"unidad_medida\":\"Pesos\",\"serie\":[{\"fecha\":\"2017-04-07T04:00:00.000Z\",\"valor\":26483.88},{\"fecha\":\"2017-04-06T04:00:00.000Z\",\"valor\":26482.18},{\"fecha\":\"2017-04-05T04:00:00.000Z\",\"valor\":26480.47},{\"fecha\":\"2017-04-04T04:00:00.000Z\",\"valor\":26478.76},{\"fecha\":\"2017-04-03T04:00:00.000Z\",\"valor\":26477.06},{\"fecha\":\"2017-04-02T04:00:00.000Z\",\"valor\":26475.35},{\"fecha\":\"2017-04-01T04:00:00.000Z\",\"valor\":26473.65},{\"fecha\":\"2017-03-31T04:00:00.000Z\",\"valor\":26471.94},{\"fecha\":\"2017-03-30T04:00:00.000Z\",\"valor\":26470.23},{\"fecha\":\"2017-03-29T04:00:00.000Z\",\"valor\":26468.53},{\"fecha\":\"2017-03-28T04:00:00.000Z\",\"valor\":26466.82},{\"fecha\":\"2017-03-27T04:00:00.000Z\",\"valor\":26465.12},{\"fecha\":\"2017-03-26T04:00:00.000Z\",\"valor\":26463.41},{\"fecha\":\"2017-03-25T04:00:00.000Z\",\"valor\":26461.7},{\"fecha\":\"2017-03-24T04:00:00.000Z\",\"valor\":26460},{\"fecha\":\"2017-03-23T04:00:00.000Z\",\"valor\":26458.29},{\"fecha\":\"2017-03-22T04:00:00.000Z\",\"valor\":26456.59},{\"fecha\":\"2017-03-21T04:00:00.000Z\",\"valor\":26454.88},{\"fecha\":\"2017-03-20T04:00:00.000Z\",\"valor\":26453.18},{\"fecha\":\"2017-03-19T04:00:00.000Z\",\"valor\":26451.47},{\"fecha\":\"2017-03-18T04:00:00.000Z\",\"valor\":26449.77},{\"fecha\":\"2017-03-17T04:00:00.000Z\",\"valor\":26448.06},{\"fecha\":\"2017-03-16T04:00:00.000Z\",\"valor\":26446.36},{\"fecha\":\"2017-03-15T04:00:00.000Z\",\"valor\":26444.65},{\"fecha\":\"2017-03-14T04:00:00.000Z\",\"valor\":26442.95},{\"fecha\":\"2017-03-13T04:00:00.000Z\",\"valor\":26441.25},{\"fecha\":\"2017-03-12T05:00:00.000Z\",\"valor\":26439.54},{\"fecha\":\"2017-03-11T05:00:00.000Z\",\"valor\":26437.84},{\"fecha\":\"2017-03-10T05:00:00.000Z\",\"valor\":26436.13},{\"fecha\":\"2017-03-09T05:00:00.000Z\",\"valor\":26434.43},{\"fecha\":\"2017-03-08T05:00:00.000Z\",\"valor\":26429.73}]}")));
	}

}

