/*
 * 这是一个用于管理菜单的JavaScript文件。
 * 它定义了一个名为menu的对象，该对象包含一个名为list的方法。
 * list方法返回一个数组，数组中的每个元素代表一个角色的菜单配置。
 */
const menu = {
    list() {
        return [{
            "backMenu": [{
                "child": [{"buttons": ["新增", "查看", "修改", "删除"], "menu": "管理员用户列表", "tableName": "users"}],
                "menu": "管理员信息管理"
            },{
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "求职者用户列表",
                    "tableName": "generaluser"
                },{
                        "buttons": ["新增","查看", "修改", "删除"],
                        "menu": "求职者信息列表",
                        "tableName": "jobseekerinfo"
                    },{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "学历信息列表",
                    "tableName": "educationinfo"
                }
                ],
                "menu": "求职者信息管理"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "企业信息列表",
                    "tableName": "companyinfo"
                },{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "岗位类别列表",
                    "tableName": "job_category"
                }],
                "menu": "企业信息管理"
            }, {
                "child": [{"buttons": ["查看", "修改", "删除"], "menu": "招聘信息列表", "tableName": "employment"},
                    {"buttons": ["查看", "删除", "审核"], "menu": "应聘信息列表", "tableName": "application"},
                    {"buttons": ["查看", "修改", "删除"], "menu": "面试信息列表", "tableName": "interview"}
                ],
                "menu": "招应聘信息管理"
            },{
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "招聘信息评论管理",
                    "tableName": "job_post_comments"
                }, {
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "求职者评论管理",
                    "tableName": "job_seeker_comments"
                }], "menu": "评论管理"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "配置管理",
                    "tableName": "config"
                }, {"buttons": ["新增", "查看", "修改", "删除"], "menu": "新闻资讯列表", "tableName": "news"},
                {
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "友情链接列表",
                    "tableName": "friendly_links"
                },
                {"buttons": ["新增", "查看", "修改", "删除"], "menu": "收藏列表", "tableName": "storeup"},
                {"buttons": ["新增", "查看", "修改", "删除"], "menu": "论坛帖子列表", "tableName": "forum"}],
                "menu": "系统管理"
            }
              ],
            "frontMenu": [{
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除", "应聘"],
                    "menu": "招聘信息列表",
                    "tableName": "employment"
                }], "menu": "招聘信息模块"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "求职者信息列表",
                    "tableName": "jobseekerinfo"
                }],
                "menu": "求职者信息模块"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "友情链接列表",
                    "tableName": "friendly_links"
                }],
                "menu": "友情链接模块"
            }],
            "roleName": "管理员",
            "tableName": "users"
        }, {
            "backMenu": [{
                "child": [{
                    "buttons": ["新增","查看", "修改", "删除"],
                    "menu": "求职者信息",
                    "tableName": "jobseekerinfo"
                }], "menu": "求职者个人信息"
            }, {
                "child": [{
                    "buttons": ["查看","应聘"],
                    "menu": "招聘信息查看",
                    "tableName": "employment"
                }], "menu": "招聘信息"
            },
                {
                "child": [{"buttons": ["查看"], "menu": "我的应聘信息", "tableName": "application"}],
                "menu": "我的应聘"
            },
            ],
            "frontMenu": [{
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除", "应聘"],
                    "menu": "招聘信息列表",
                    "tableName": "employment"
                }], "menu": "招聘信息模块"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "求职者信息列表",
                    "tableName": "jobseekerinfo"
                }],
                "menu": "求职者信息模块"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "友情链接列表",
                    "tableName": "friendly_links"
                }],
                "menu": "友情链接模块"
            }],
            "roleName": "求职者",
            "tableName": "generaluser"
        }, {
            "backMenu": [{
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "招聘信息",
                    "tableName": "employment"
                }], "menu": "招聘信息管理"
            }, {
                "child": [{"buttons": ["查看", "删除", "审核","查询用户"], "menu": "应聘信息", "tableName": "application"}],
                "menu": "应聘信息管理"
            }],
            "frontMenu": [{
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除", "应聘"],
                    "menu": "招聘信息列表",
                    "tableName": "employment"
                }], "menu": "招聘信息模块"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "求职者信息列表",
                    "tableName": "jobseekerinfo"
                }],
                "menu": "求职者信息模块"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "友情链接列表",
                    "tableName": "friendly_links"
                }],
                "menu": "友情链接模块"
            }],
            "roleName": "企业",
            "tableName": "companyinfo"
        }]
    }
}
export default menu;
