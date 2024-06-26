/**
 * 前台首页推荐信息，求职者、招聘信息
 * @param tablename	表名
 * @param page		页数
 * @param limit		条数
 * @paramcallback	回调
 */
function recommend(tablename, type ,callback) {
	if(!type){
		httpJson(`${tablename}/list?page=1&limit=6`, 'GET', '', callback);
	}else{
		httpJson(`${tablename}/autoSort?limit=6`, 'GET', '', callback);
	}
}
