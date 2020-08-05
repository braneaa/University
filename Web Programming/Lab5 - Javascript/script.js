var arr = [];

function addToArray(){
	x = document.getElementById("number").value;
	arr.push(x);
}

function sortNumber(a, b) {
  return a - b;
}

function createTable(){
	
	arr.sort(sortNumber);
	
	document.write('<table width="100%" border="1px solid black">');
	document.write('<tr>');
	document.write('<th>Col1</th>');
	document.write('<th>Col2</th>');
	document.write('<th>Col3</th>');
	document.write('<th>Col4</th>');
	document.write('<th>Col5</th>');
	document.write('</tr>');
	
	for(var i = 0; i < arr.length; i=i+5)
	{
		document.write('<tr>');
		
		document.write('<td>' + arr[i] + '</td>');
		if(arr[i+1] == undefined)
			document.write('<td>' + 0 + '</td>');
		else document.write('<td>' + arr[i+1] + '</td>');
		if(arr[i+2] == undefined)
			document.write('<td>' + 0 + '</td>');
		else document.write('<td>' + arr[i+2] + '</td>');
		if(arr[i+3] == undefined)
			document.write('<td>' + 0 + '</td>');
		else document.write('<td>' + arr[i+3] + '</td>');
		if(arr[i+4] == undefined)
			document.write('<td>' + 0 + '</td>');
		else document.write('<td>' + arr[i+4] + '</td>');
		
		document.write('</tr>');
	}
	
	document.write('</table>');
	
}