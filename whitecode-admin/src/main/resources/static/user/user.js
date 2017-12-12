$(document).ready(function () {
    // 初始化dataTables，并加载数据
    var table = $("#dataTable").DataTable({
        "paging": true,         //开启表格分页
        "lengthChange": false, //是否允许用户改变表格每页显示的记录数
        "searching": false,    //禁用搜索
        "ordering": false,     //全局禁用排序
        "deferRender": true,   //延迟渲染
        "autoWidth": false,    //开启自适应宽度
        "processing": true,
        "serverSide": true,
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.16/i18n/Chinese.json"
        },
        "ajax": {
            "url": "/user/listByPage",
            "type": "POST",
            "data": function (d) {
                d.status=$("#status").val();
                d.username=$("#username").val().trim();
            }
        },
        "columns": [
            { "data": "userId" },
            {
                "data": "username",
                "render": function (data, type, row, meta) {
                    return	data='<a href="/user/edit/'+row.userId+'">'+ row.username+'</a>';
                }
            },
            { "data": "name" },
            { "data": "creator"},
            {"data": "createDateTime"},
            { "data": "modifier"},
            { "data": "modifyDateTime"},
            {
                "data": "status",
                "render" : function(data, type, row, meta) {
                    (data == "NORMAL") ?
                        data ="<span class='label label-primary'>正常</span>" :
                        data ="<span class='label label-danger'>锁定</span>";
                    return data;
                }
            },
            {
                "data" : null,
                "render":function(data, type, row, meta){
                    return	data='<button class="btn btn-primary btn-xs" id="deleteOne" title="删除" data-id='+ row.id +'><i class="glyphicon glyphicon-trash"></i></button> ' +
                        '<button class="btn btn-primary btn-xs" id="editOne" title="编辑"  data-id='+ row.id +'><i class="glyphicon glyphicon-edit"></i></button>';
                }
            }
        ]
    });


    // 打开编辑页面
    $(document).delegate('#editOne','click',function() {
        var id=$(this).data("id");
        window.open("/api/user/edit/"+id,"_self");
    });
    // 重置查询条件
    $(document).delegate('#reset','click',function() {
        $("#username").val("");
        $("#status").val("");
    });
    // 查询操作
    $(document).delegate('#search','click',function() {
        table.ajax.reload();
    });
    //单行删除操作
    $(document).delegate('#deleteOne','click',function() {
        var id = $(this).data("id");
        layer.confirm('您确定要删除当前信息吗？', {icon: 3, title:'提示信息'}, function(index) {
            $.ajax({
                url: "/api/user/" + id,
                async: true,
                type: "DELETE",
                dataType: "json",
                cache: false,    //不允许缓存
                success: function(data) {
                    if (data.code == 0) {
                        layer.msg("删除成功", {icon: 1, time: 2000});
                    }else {
                        layer.msg("删除失败", {icon: 2, time: 2000});
                    }
                    table.ajax.reload();
                    layer.close(index);
                },
                error: function () {
                    layer.msg("数据异常", {time: 1500},function(){
                        layer.close(index);
                    });
                }
            });
        });
    });
});