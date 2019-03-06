var cacheStr = window.sessionStorage.getItem("cache"),
    oneLoginStr = window.sessionStorage.getItem("oneLogin");
layui.use(['form','jquery',"layer"],function() {
    var form = layui.form,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;



    //公告层
    function showNotice(){
        layer.open({
            type: 1,
            title: "系统公告",
            area: '300px',
            shade: 0.8,
            id: 'LAY_layuipro',
            btn: ['火速围观'],
            moveType: 1,
            content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red">请使用模版前请务必仔细阅读首页右下角的《更新日志》，避免使用中遇到一些简单的问题造成困扰。</p></pclass></p><p>1.0发布以后发现很多朋友将代码上传到各种素材网站，当然这样帮我宣传我谢谢大家，但是有部分朋友上传到素材网站后将下载分值设置的相对较高，需要朋友们充钱才能下载。本人发现后通过和站长、网站管理员联系以后将分值调整为不需要充值才能下载或者直接免费下载。在此郑重提示各位：<span class="layui-red">本模版已进行作品版权证明，不管以何种形式获取的源码，请勿进行出售或者上传到任何素材网站，否则将追究相应的责任。</span></p></div>',
            success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
                btn.on("click",function(){
                    tipsShow();
                });
            },
            cancel: function(index, layero){
                tipsShow();
            }
        });
    }
    $(".showNotice").on("click",function(){
        showNotice();
    })


})