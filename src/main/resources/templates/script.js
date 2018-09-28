$(document).ready(function() {

    var apiRoot = 'http://localhost:8080/v1/mathTask/';
    var datatableRowTemplate = $('[data-datatable-row-template]').children()[0];
    var tasksContainer = $('[data-tasks-container]');

    // init
    getAllTasks();

    function createElement(data) {
        var element = $(datatableRowTemplate).clone();

        element.attr('data-task-id', data.id);
        element.find('[data-task-firstNumber-section] [data-task-firstNumber-paragraph]').value(data.a);
        element.find('[data-task-firstNumber-section] [data-task-firstNumber-input]').val(data.a);

        element.find('[data-task-taskType-section] [data-task-taskType-paragraph]').text(data.taskType);
        element.find('[data-task-taskType-section] [data-task-taskType-input]').val(data.taskType);

        element.find('[data-task-secondNumber-section] [data-task-secondNumber-paragraph]').value(data.b);
        element.find('[data-task-secondNumber-section] [data-task-secondNumber-input]').val(data.b);

        element.find('[data-task-taskLvl-section] [data-task-taskLvl-paragraph]').value(data.taskLvl);
        element.find('[data-task-taskLvl-section] [data-task-taskLvl-input]').val(data.taskLvl);

        element.find('[data-task-reply-section] [data-task-reply-paragraph]').value(data.reply);
        element.find('[data-task-reply-section] [data-task-reply-input]').val(data.reply);
        return element;
    }

    function handleDatatableRender(data) {
        tasksContainer.empty();
        data.forEach(function(task) {
            createElement(task).appendTo(tasksContainer);
        });
    }

    function getAllTasks() {
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
        var firstNumber = parentEl.find('[data-task-firstNumber-input]').val();
        var taskType = parentEl.find('[data-task-taskType-input]').val();
        var secondNumber = parentEl.find('[data-task-secondNumber-input]').val();
        var taskLvl = parentEl.find('[data-task-taskLvl-input]').val();
        var reply = parentEl.find('[data-task-reply-input]').val();

        var requestUrl = apiRoot + 'updateMathTask';

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
                parentEl.find('[data-task-name-paragraph]').value(a);
                parentEl.find('[data-task-taskType-paragraph]]').text(taskType);
                parentEl.find('[data-task-secondNumber-paragraph]').value(b);
                parentEl.find('[data-task-taskLvl-paragraph]').value(taskLvl);
                parentEl.find('[data-task-reply-paragraph]').value(reply);


            }
        });
    }

  function handleTaskDeleteRequest() {
    var parentEl = $(this).parent().parent();
    var taskId = parentEl.attr('data-task-id');
    var requestUrl = apiRoot + 'deleteTask';

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

    var firstNumber = $(this).find('[name="firstNumber"]').val();
    var taskType = $(this).find('[name="taskType"]').val();
    var secondNumber = $(this).find('[name="secondNumber"]').val();
    var taskLvl = $(this).find('[name="taskLvl"]').val();
    var reply = $(this).find('[name="reply"]').val();


    var requestUrl = apiRoot + 'createMathTask';

    $.ajax({
      url: requestUrl,
      method: 'POST',
      processData: false,
      contentType: "application/json; charset=utf-8",
      dataType: 'json',
      data: JSON.stringify({
          a: firstNumber,
          taskType: taskType,
          b: secondNumber,
          taskLvl: taskLvl,
          reply: reply
      }),
      complete: function(data) {
        if(data.status === 200) {
          getAllTasks();
        }
      }
    });
  }

  function toggleEditingState() {
    var parentEl = $(this).parent().parent();
    parentEl.toggleClass('datatable__row--editing');

    var firstNumber = parentEl.find('[data-task-firstNumber-paragraph]').value();
    var taskType = parentEl.find('[data-task-taskType-paragraph]').text();
    var secondNumber = parentEl.find('[data-task-secondNumber-paragraph]').value();
    var taskLvl = parentEl.find('[data-task-taskLvl-paragraph]').value();
    var reply = parentEl.find('[data-task-reply-paragraph]').value();

    parentEl.find('[data-task-firstNumber-input]').val(firstNumber);
    parentEl.find('[data-task-taskType-input]').val(taskType);
    parentEl.find('[data-task-secondNumber-input]').val(secondNumber);
    parentEl.find('[data-task-taskLvl-input]').val(taskLvl);
    parentEl.find('[data-task-reply-input]').val(reply);

  }

  $('[data-task-add-form]').on('submit', handleTaskSubmitRequest);

  tasksContainer.on('click','[data-task-edit-button]', toggleEditingState);
  tasksContainer.on('click','[data-task-edit-abort-button]', toggleEditingState);
  tasksContainer.on('click','[data-task-submit-update-button]', handleTaskUpdateRequest);
  tasksContainer.on('click','[data-task-delete-button]', handleTaskDeleteRequest);
});
