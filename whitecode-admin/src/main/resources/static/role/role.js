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
            "url": "/role/listByPage",
            "type": "POST",
            "data": function (d) {
                d.enable=$("#enable").val();
                d.roleName=$("#roleName").val().trim();
            }
        },
        "columns": [
            { "data": "roleId" },
            {
                "data": "roleName",
                "render": function (data, type, row, meta) {
                    return	data='<a href="/role/edit/'+row.roleId+'">'+ row.roleName+'</a>';
                }
            },
            {
                "data": "enable",
                "render" : function(data, type, row, meta) {
                    (data == "YES") ?
                        data ="<span class='label label-primary'>可用</span>" :
                        data ="<span class='label label-danger'>不可用</span>";
                    return data;
                }
            },
            { "data": "creator"},
            {"data": "createDateTime"},
            { "data": "modifier"},
            { "data": "modifyDateTime"},
            {
                "data" : null,
                "render":function(data, type, row, meta){
                    return	data='<button class="btn btn-primary btn-xs" id="deleteOne" title="删除" data-id='+ row.roleId +'><i class="glyphicon glyphicon-trash"></i></button> ' +
                        '<button class="btn btn-primary btn-xs" id="editOne" title="编辑"  data-id='+ row.roleId +'><i class="glyphicon glyphicon-edit"></i></button>';
                }
            }
        ]
    });

    // 重置查询条件
    $(document).delegate('#reset','click',function() {
        $("#roleName").val("");
        $("#enable").val("");
    });
    // 查询操作
    $(document).delegate('#search','click',function() {
        table.ajax.reload();
    });
});