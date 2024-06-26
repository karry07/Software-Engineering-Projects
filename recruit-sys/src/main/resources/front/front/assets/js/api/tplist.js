/**
 * 获取指定表的列表
 * @param {string} tablename 表名
 * @param {number} page 页数
 * @param {number} limit 每页的条数
 * @param {string} searchname 搜索字段名
 * @param {string} searchval 搜索值
 * @param {Function} callback 请求成功后的回调函数
 */
function goodsList(tablename, page, limit, searchname, searchval, callback) {
    if (searchname) {
        var url = `${tablename}/list?page=${page}&limit=${limit}&${searchname}=%${searchval}%`
        url = encodeURI(url)
        httpJson(url, 'GET', '', callback);
    } else {
        httpJson(`${tablename}/list?page=${page}&limit=${limit}`, 'GET', '', callback);
    }
}

//具体详情
function detailFun(tablename, id, callback) {
    httpJson(`${tablename}/detail/${id}`, 'GET', '', callback);
}

//更新
function updateFun(tablename, data, callback) {
    httpJson(`${tablename}/update`, 'POST', data, callback);
}

/**
 * 获取指定表的列表
 * @param {string} tablename 表名
 * @param {number} page 页数
 * @param {number} limit 每页的条数
 * @param {string} searchname 搜索字段名
 * @param {string} searchval 搜索值
 * @param {Function} callback 请求成功后的回调函数
 */
function tplist(tablename, page, limit, searchname, searchval, callback) {
    if (searchname) {
        var url = `${tablename}/list?page=${page}&limit=${limit}&${searchname}=${searchval}`
        url = encodeURI(url)
        httpJson(url, 'GET', '', callback);
    } else {
        httpJson(`${tablename}/list?page=${page}&limit=${limit}`, 'GET', '', callback);
    }
}

//具体详情
function tpDetail(tablename, id, callback) {
    httpJson(`${tablename}/detail/${id}`, 'GET', '', callback);
}

//列表
function tpPage(tablename, callback) {
    httpJson(`${tablename}/list?page=1`, 'GET', '', callback);
}

//添加
function addFun(tableName, data, callback) {
    httpJson(`${tableName}/add`, 'POST', data, callback);
}


// 点赞
function thumbsUp(tableName, id, type, callback) {
    httpJson(`${tableName}/thumbsup/${id}?type=${type}`, 'GET', '', callback);
}

// 评论
function commentList(tableName, refid, callback) {
    if (tableName == 'jobseekerinfo') {
        httpJson(`job_seeker_comments/list?refid=${refid}`, 'GET', '', callback);
    } else if (tableName == 'employement') {
        httpJson(`job_post_comments/list?refid=${refid}`, 'GET', '', callback);
    } else {
        httpJson(`${tableName}/list?refid=${refid}`, 'GET', '', callback)
    }
}

// 添加评论
function addComment(tableName, data, callback) {
    if (tableName == 'jobseekerinfo') {
        httpJson(`job_seeker_comments/add`, 'POST', data, callback);
    } else if (tableName == 'employement') {
        httpJson(`job_post_comments/add`, 'POST', data, callback);
    } else {
        httpJson(`${tableName}/add`, 'POST', data, callback);
    }

}

// 收藏
function storeUpAdd(tableName, data, callback) {
    httpJson(`storeup/add`, 'POST', data, callback);
}

// 取消收藏
function storeUpDelete(id, callback) {
    httpJson(`storeup/deleteById/${id}`, 'DELETE', null, callback);
}

// 检查收藏状态
function checkStoreStatus(tableName, data, callback) {
    httpJson(`storeup/check`, 'POST', data, callback);
}

//提交审核内容
function submitAudit(tableName, data, callback) {
    httpJson(`application/audit`, 'POST', data, callback);
}


// 获取某表某个字段列表
function getOption(tableName, columnName, callback) {
    httpJson(`option/${tableName}/${columnName}`, 'GET', '', callback);
}

function getFollow(tableName, columnName, columnValue, callback) {
    httpJson(`follow/${tableName}/${columnName}?columnValue=${columnValue}`, 'GET', '', callback);
}
