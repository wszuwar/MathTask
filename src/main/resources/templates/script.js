$(document).ready(function() {

    varapiRoot = 'http://localhost:8080/v1/mathTask';

    var datatableRowTemplate = $('[data-datatable-row-template]').children()[0];
    var tasksContainer = $('[data-tasks-container]');


    //init
    getAllMathTasks();


    function createElement(data) {
        var element = $(datatableRowTemplate).clone();

        element.attr('data-task-id', data.id);
        element.find('[data-task-name-section] [data-task-firstNum-paragraph]').number(data.numberA);
        element.find('[data-task-name-section] [data-task-firstNum-input]').val(data.numberA);

        element.find('[data-task-content-section] [data-task-type-paragraph]').text(data.type);
        element.find('[data-task-content-section] [data-task-type-input]').val(data.type);

        element.find('[data-task-name-section] [data-task-secondNum-paragraph]').number(data.numberB);
        element.find('[data-task-name-section] [data-task-secondNum-input]').val(data.numberB);

        return element;
    }

    function handleDatatableRender(data) {
        tasksContainer.empty();
        data.forEach(function(task) {
            createElement(task).appendTo(tasksContainer);
        });
    }
    function getAllMathTasks() {
        var requestUrl = apiRoot + 'getMathTasks';

        $.ajax({
            url: requestUrl,
            method: 'GET',
            success: handleDatatableRender
        });
    }
    function handleTaskUpdateRequest() {
        var parentEl = $(this).parent().parent();
        var taskId = parentEl.attr('data-task-id');

        var firstNum = parentEl.find('[data-task-firstNum-input]').val();
        var type = parentEl.find('[data-task-type-input]').val();
        var  secondNum = parentEl.find('[data-task-secondNum-input]').val();
        var requestUrl = apiRoot + 'updateMathTask';

        $.ajax({
            url: requestUrl,
            method: "PUT",
            processData: false,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify({
                id: taskId,
                a: firstNum,
                type: type,
                b:secondNum
            }),
            success: function(data) {
                parentEl.attr('data-task-id', data.id).toggleClass('datatable__row--editing');
                parentEl.find('[data-task-firstNum-paragraph]').number(firstNum);
                parentEl.find('[data-task-type-paragraph]').text(type);
                parentEl.find('[data-task-secondNum-paragraph]').number(secondNum);
            }
        });
    }
    function handleTaskDeleteRequest() {
        var parentEl = $(this).parent().parent();
        var taskId = parentEl.attr('data-task-id');
        var requestUrl = apiRoot + 'deleteMathTask';

        $.ajax({
            url: requestUrl + '/?' + $.param({
                taskId: taskId
            }),
            method: 'DELETE',
            success: function() {
                parentEl.slideUp(400, function() { parentEl.remove(); });
            }
        })
    }
    function handleTaskSubmitRequest(event) {
        event.preventDefault();

        var firstNum = $(this).find('[name="numberA"]').val();
        var type = $(this).find('[name="type"]').val();
        var secondNum = $(this).find('[name="numberB"]').val();

        var requestUrl = apiRoot + 'createMathTask';

        $.ajax({
            url: requestUrl,
            method: 'POST',
            processData: false,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify({
                id: taskId,
                a: firstNum,
                type: type,
                b:secondNum
            }),
            complete: function(data) {
                if(data.status === 200) {
                    getAllMathTasks();
                }
            }
        });
    }
    function toggleEditingState() {
        var parentEl = $(this).parent().parent();
        parentEl.toggleClass('datatable__row--editing');

        var firstNum = parentEl.find('[data-task-firstNum-paragraph]').number();
        var type =  parentEl.find('[data-task-type-paragraph]').text();
        var secondNum =  parentEl.find('[data-task-secondNum-paragraph]').number();

        parentEl.find('[data-task-firstNum-input]').val(firstNum);
        parentEl.find('[data-task-type-input]').val(type);
        parentEl.find('[data-task-secondNum-input]').val(secondNum);
    }




    $('[data-task-add-form]').on('submit', handleTaskSubmitRequest);
    tasksContainer.on('click','[data-task-edit-button]', toggleEditingState);
    tasksContainer.on('click','[data-task-edit-abort-button]', toggleEditingState);
    tasksContainer.on('click','[data-task-submit-update-button]', handleTaskUpdateRequest);
    tasksContainer.on('click','[data-task-delete-button]', handleTaskDeleteRequest);
});