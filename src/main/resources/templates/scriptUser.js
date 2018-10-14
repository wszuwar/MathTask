$(document).ready(function() {

    var apiRoot = 'http://localhost:8080/v2/';
    var datatableRowTemplate = $('[data-datatable-row-template]').children()[0];
    var tasksContainer = $('[data-tasks-container]');

    var availableTasks = {};
// init
    getAllMathTasks();

    function createElement(data) {
        var element = $(datatableRowTemplate).clone();

        element.attr('data-task-id', data.id);
        element.find('[data-task-firstNumber-section] [data-task-firstNumber-paragraph]').text(data.a);


        element.find('[data-task-taskType-section] [data-task-taskType-paragraph]').text(data.taskType);


        element.find('[data-task-secondNumber-section] [data-task-secondNumber-paragraph]').text(data.b);

        element.find('[data-task-reply-section] [data-task-reply-paragraph]').text(data.reply);
        element.find('[data-task-reply-section] [data-task-reply-input]').valueOf(data.reply);
        return element;
    }

    function handleDatatableRender(taskData) {
        tasksContainer.empty();
        taskData.forEach(function(mathTask) {
            var $datatableRowEl = createElement(mathTask);
            $datatableRowEl.appendTo(tasksContainer);
        });
    }
    function getAllMathTasks() {
        var requestUrl = apiRoot + 'mathTasks/{mathTaskLvl}';

        $.ajax({
            url: requestUrl,
            method: 'GET',
            success: function(mathTasks) {
                mathTasks.forEach(mathTask => {
                    availableTasks[mathTask.id] = mathTask;
            });
                handleDatatableRender(mathTasks);
            }
        });
    }

    function handleTaskUpdateRequest() {
        var parentEl = $(this).parents('[data-task-id]');
        var taskId = parentEl.attr('data-task-id');
        var firstNumber = parentEl.find('[data-task-firstNumber-input]').val();
        var taskType = parentEl.find('[data-task-taskType-input]').val();
        var secondNumber = parentEl.find('[data-task-secondNumber-input]').val();
        var taskLvl = parentEl.find('[data-task-taskLvl-input]').val();
        var reply = parentEl.find('[data-task-reply-input]').val();

        var requestUrl = apiRoot + 'mathTasks';

        $.ajax({
            url: requestUrl,
            method: "PUT",
            processData: false,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify({
                id: taskId,
                a: firstNumber,
                taskType: taskType,
                b: secondNumber,
                taskLvl: taskLvl,
                reply: reply

            }),
            success: function(data) {
                parentEl.attr('data-task-id', data.id).toggleClass('datatable__row--editing');
                parentEl.find('[data-task-firstNumber-input]').val();
                parentEl.find('[data-task-taskType-input]').val();
                parentEl.find('[data-task-secondNumber-input]').val();
                parentEl.find('[data-task-taskLvl-input]').val();
                parentEl.find('[data-task-reply-paragraph]').text(reply);

            }
        });
    }

    function toggleEditingState() {
        var parentEl = $(this).parent().parent();
        parentEl.toggleClass('datatable__row--editing');


        var firstNumber = parentEl.find('[data-task-firstNumber-paragraph]').text();
        var taskType = parentEl.find('[data-task-taskType-paragraph]').text();
        var secondNumber = parentEl.find('[data-task-secondNumber-paragraph]').text();
        var taskLvl = parentEl.find('[data-task-taskLvl-paragraph]').text();
        var reply = parentEl.find('[data-task-reply-paragraph]').text();

        parentEl.find('[data-task-firstNumber-paragraph]').text();
        parentEl.find('[data-task-taskType-paragraph]').text();
        parentEl.find('[data-task-secondNumber-paragraph]').text();
        parentEl.find('[data-task-taskLvl-paragraph]').text();
        parentEl.find('[data-task-reply-input]').valueOf(reply);



    }

    tasksContainer.on('click','[data-task-edit-button]', toggleEditingState);
    tasksContainer.on('click','[data-task-edit-abort-button]', toggleEditingState);
    tasksContainer.on('click','[data-task-submit-update-button]', handleTaskUpdateRequest);
});
