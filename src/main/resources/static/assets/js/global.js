$(document).ready(function () {
    readOwn();
});

//获取用户信息
function readOwn() {
    $.post('/api/user/readOwn', function (result) {
        if (result.code === 0) {
            //显示用户信息
            $('#userName').html(result.data.name);

            //首页多一些逻辑判断
            var pathname = window.location.pathname;
            if ("/" === pathname || "/index.html" === pathname) {
                $('*[permission-control]').addClass('layui-hide');
                $.each(result.data.permissionList, function (i, item) {
                    $("*[permission-control='" + item.path + "']").removeClass('layui-hide');
                });
                var menuList = $('.layui-side .layui-nav-child');
                menuList.parent().addClass('layui-hide');
                menuList.each(function () {
                    if ($(this).children('dd').not('.layui-hide').length > 0) {
                        $(this).parent().removeClass('layui-hide');
                    }
                });
                //设置用户信息
                $('#userCreateTime').html(result.data.createTime);
                $('#userLoginTime').html(result.data.loginTime);
                $('#userPreLoginTime').html(result.data.preLoginTime);
                $('#userUsername').html(result.data.username);
                $('#userLoginCount').html(result.data.loginCount);
            }
        }
        //用户未激活
        else if (result.code === 1103) {
            openChangePasswordBox(false);
        }
        //未登录
        else {
            openLoginBox();
        }
    });
}

var loginLayerIndex;

/**
 * 打开登录框
 */
function openLoginBox() {
    loginLayerIndex = layui.layer.open({
        type: 1,
        move: false,
        shade: [0.95, '#000'],
        closeBtn: 0,
        title: '登录',
        content: $('#loginBox').html(),
        area: ['400px', '300px'],
        success: function () {
            $('#loginUsername').val($.cookie('username'));
            $('#loginPassword').val($.cookie('password'));
            layui.form.render(null, 'loginBoxFilter');
        }
    });
}

/**
 * 登录
 */
layui.form.on('submit(loginBoxFormFilter)', function (data) {
    $.post('/api/user/login', {username: data.field.username, password: data.field.password}, function (result) {
        layui.layer.msg(result.message);
        if (result.code === 0) {
            $.cookie('username', data.field.username, {expires: 30});
            $.cookie('password', data.field.password, {expires: 30});
            layui.layer.close(loginLayerIndex);
            readOwn();
        }
    });
    return false;
});

var changePasswordLayerIndex;

/**
 * 打开修改密码框
 * @param isAllowClose 是否允许关闭
 */
function openChangePasswordBox(isAllowClose) {
    changePasswordLayerIndex = layui.layer.open({
        type: 1,
        move: false,
        shade: [0.95, '#000'],
        closeBtn: true === isAllowClose ? 1 : 0,
        title: '修改密码',
        content: $('#changePasswordBox').html(),
        area: ['400px', '350px']
    });
}

/**
 * 修改密码
 */
layui.form.on('submit(changePasswordBoxFormFilter)', function (data) {
    $.post('/api/user/changePassword', {
        username: data.field.username,
        password: data.field.password,
        firstPassword: data.field.firstPassword,
        secondPassword: data.field.secondPassword
    }, function (result) {
        layui.layer.msg(result.message);
        if (result.code === 0) {
            layui.layer.close(changePasswordLayerIndex);
            readOwn();
        }
    });
    return false;
});

//切换或者增加一个新的tab
function changeTab(tabId, title, url) {
    var element = layui.element;
    sideShadowHide();
    var tab = $(".layui-tab-title li[lay-id=" + tabId + "]");
    if (tab == null || tab.length === 0) {
        $.get(url, function (data) {
            element.tabAdd('tabs', {
                title: title,
                content: data,
                id: tabId
            });
            element.tabChange('tabs', tabId);
        });
    } else {
        element.tabChange('tabs', tabId);
    }
}

//退出登录
function logout() {
    layer.confirm('确定退出登录吗?', {
        title: '',
        btnAlign: 'c',
        closeBtn: 0,
        move: false,
        shade: [0.95, '#000']
    }, function (index) {
        $.post('/api/user/logout', function (result) {
            layer.msg(result.message);
            if (result.code === 0) {
                layer.close(index);
                readOwn();
            }
        });
    });
}

function sideMore() {
    $('.layui-side-shadow').addClass('layui-side-shadow-show');
    $(".layui-side").addClass('layui-side-show');
}

//隐藏
function sideShadowHide() {
    $('.layui-side-shadow').removeClass('layui-side-shadow-show');
    $(".layui-side").removeClass('layui-side-show');
}


