
function check_items(){

	var numChecked = 0;
	for (var i = 0; i < document.getElementById('add_items').elements.length; i++){
		if (new RegExp('bk').test(document.getElementById('add_items').elements[i].name)){
			if (document.getElementById('add_items').elements[i].checked == true){
				numChecked++;
			}
		}
	}
	if (numChecked == 0){
		window.alert('No items selected, please select items')
	}else{
		document.getElementById('add_items').submit();
	}
}