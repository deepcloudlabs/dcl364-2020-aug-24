import com.example.ccc.service.RandomNumberService;

module com.example.bbb {
	requires com.example.ccc;
	uses RandomNumberService;
}