var replyManager = (function() {
	var getAll = function(obj, callback) {
		console.log("get All.....");
		$.getJSON("/myapp2/replies/" + obj, callback);
	};

	var add = function(obj, callback) {
		console.log("add.....");
		$.ajax({
			type: "post",
			url: "/myapp2/replies/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: callback
		});
	};
	var update = function(obj, callback) {
		$.ajax({
			type: "put",
			url: "/myapp2/replies/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: callback
		});
	};

	var remove = function(obj, callback) {
		$.ajax({
			type: "delete",
			url: "/myapp2/replies/" + obj.bno + "/" + obj.rno,
			dataType: "json",
			contentType: "application/json",
			success: callback
		});
	};
	return {
		getAll: getAll,
		add: add,
		update: update,
		remove: remove
	};
})();



