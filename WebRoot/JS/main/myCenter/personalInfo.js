$(function(){
	var province = $('input[name="province"]').val();
	var city = $('input[name="city"]').val();
	var county = $('input[name="county"]').val();
	var town = $('input[name="town"]').val();
	fillDistrict(province, city, county, town);
});