$(document).ready(function() {

    const apiRoot = 'http://localhost:8080/v1/';

    const datatableRowTemplate = $('[data-datatable-row-template]').children()[0];
    const $tasksContainer = $('[data-tasks-container]');

    var availableBoards = {};
    var availableTasks = {};

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
        var  secondNum = parentEl.find('[data-task-secondNum-input]');
        var requestUrl = apiRoot + 'updateTask';

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
                parentEl.find('[data-task-secondNum-input]').number(secondNum);
            }
        });
    }


    tasksContainer.on('click','[data-task-edit-button]', toggleEditingState);
    tasksContainer.on('click','[data-task-edit-abort-button]', toggleEditingState);
    tasksContainer.on('click','[data-task-submit-update-button]', handleTaskUpdateRequest);
    tasksContainer.on('click','[data-task-delete-button]', handleTaskDeleteRequest);
});