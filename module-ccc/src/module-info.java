module com.example.ccc {
	exports com.example.ccc.service;
	provides com.example.ccc.service.RandomNumberService with com.example.ccc.service.business.SimpleRandomNumberService, com.example.ccc.service.business.SecureRandomNumberService;
}