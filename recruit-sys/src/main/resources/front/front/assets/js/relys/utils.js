/**
 * 获取传递参数值(修改支持中文)
 * @param {Object} name 参数名称
 */
function getParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURI(r[2]); //对参数进行decodeURI解码
	return null;
}

