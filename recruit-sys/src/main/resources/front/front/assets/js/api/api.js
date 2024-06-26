var preview = "否"
var baseurl = "http://localhost:8082/resumeDelivery/";
var neturl = ""
/**
 * 网络请求
 * @param {Object} url		地址
 * @param {Object} type		类型
 * @param {Object} data		数据
 * @param {Object} callback	回调函数
 */
function http(url, type, data, callback) {
	url = baseurl + url;
	$.ajax({
		url: url,
		beforeSend: function(request) {
			// 在请求发送前设置Token请求头
			request.setRequestHeader("Token", localStorage.getItem("Token"));
		},
		contentType: 'application/x-www-form-urlencoded', // 设置请求内容类型为表单数据
		data: data,
		dataType: 'json',
		type: type,
		success: function(result, status, xhr) {
			if (result.code == 0) {
				callback(result);
			} else if (result.code == 401) {
				window.parent.location.href = '../login/login.html';
				localStorage.removeItem('Token')
				alert('您还没登录，请登录')
			} else {
				alert(result.msg)
			}
		},
		error: function(xhr, status, error) {
			console.log(xhr, status, error)
		}
	});
}

/**
 * 网络请求
 * @param url  地址
 * @param type 类型
 * @param data 数据
 * @param callback 回调函数
 */
function httpJson(url, type, data, callback) {
	url = baseurl + url;
	var params = null;
	// 如果有数据，将其转换为JSON格式
	if (data) {
		var params = JSON.stringify(data);
	}
	$.ajax({
		url: url,
		beforeSend: function(request) {
			// 在请求发送前设置Token请求头
			request.setRequestHeader("Token", localStorage.getItem("Token"));
		},
		contentType: 'application/json',// 设置请求内容类型为JSON
		data: params,
		dataType: 'json', // 期望的响应数据类型
		type: type,
		success: function(result, status, xhr) {
			if (result.code == 0) { // 如果返回的code为0，调用回调函数
				callback(result);
			} else if (result.code == 401) {
				window.parent.location.href = '../login/login.html';
				localStorage.removeItem('Token')
				alert('您还没登录，请登录')
			} else {
				alert(result.msg)
			}
		},
		error: function(xhr, status, error) {
			console.log(xhr, status, error)
		}
	});
}

/**
 * 上传文件
 * @param file 文件
 * @param callback 回调函数
 */
function upload(file,callback) {
	url = baseurl + "file/upload";
	var formData = new FormData();
	// 将文件添加到FormData对象中
	formData.append('file', file);
	// 发起ajax请求
	$.ajax({
		url: url,
		type: 'post',
		data: formData,
		headers: {
			"Token": localStorage.getItem('Token')
		}, // 设置请求头的Token
		contentType: false,
		processData: false,// 不处理数据
		success: function(res) {
			if (res.code == 0) {
				callback(res);
			} else if (res.code == 401) {
				window.parent.location.href = '../login/login.html';// 如果返回的code为401，跳转到登录页面
				localStorage.removeItem('Token') // 移除Token
				alert('您还没登录，请登录')
			} else {
				alert(res.msg)
			}
		}
	})
}
