
function deleteItem(id){
	document.cookie = id + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	//document.cookie = document.cookie.replace(new RegExp(id + "=" + id), id + "=" + id + "; expires=Thu, 18 Dec 2013 12:00:00 UTC");
	document.getElementById('refresh').submit();
}