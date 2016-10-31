/**
 * 
 */
	//var w = 900;
	//var h = 600;
	var w = window.outerWidth;
	var h = window.outerHeight;
	console.log(w);
	console.log(h);
	
	var zoomrate = [1/8, 8];
	
	var selected = null;
	var selectednode = null;
	
	var node = [
	            //{text : "1번타자", parent : 1, child : [1,2,3], values: 50}
	            {text : "더미데이터", values : 100}
	];
	var links = [];
	
	setXY();
	linkNode();

	var svg = d3.select("#main").append("svg")
					.attr("width", w)
					.attr("height", h);
					
	var transform = d3.zoomIdentity;

	var force = d3.layout.force()
    .nodes(node)
    .links(links)
    .size([w,h]);
	
	var width = svg
							.attr("width"),
			height = svg
							.attr("height");
	addg = svg
							.selectAll("g")
							.data(node)
							.enter()
								.append("g")
								.attr("transform", "translate(40,0)");
	
	g = svg.selectAll("g");
	
	rect = g.append("circle")
										.attr("r", function(d) { return d.values; })
										.attr("cx", function(d) { return d.x; })
    									.attr("cy", function(d) { return d.y; });
			
	line = g.selectAll("line")
										.data(links)
										.enter()
										.append("line")
										.style("pointer-events", "none");
	
	var text = g.append("text")
			.attr("dx", function(d) { return d.x; })
			.attr("dy", function(d) { return d.y; })
			.text(function(d, i) { return d.text; });
		  				
	var drag = d3.behavior.drag()
											    .origin(function(d) { return d; })
											    .on("dragstart", dragstarted)
											    .on("drag", dragged)
											    .on("dragend", dragended);
	
	var zoom = d3.behavior.zoom()
											    .scaleExtent(zoomrate)
											    .on("zoom", zoomed);
	
	force.on("tick", tick);
	svg.call(zoom);
	rect.call(drag);
    rect.on("click", clicked);
	
	function clicked(d) {
		if (selected != d3.select(this)) {
			if (selected != undefined) {
				selected.classed("selected", false);
			}
			selected = d3.select(this);
			selectednode = d;
			d3.select(this).classed("selected", true);
		}
	}
	
	function zoomed() {
		g.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
	}
	
	function dragstarted(d) {
	  d3.event.sourceEvent.stopPropagation();
	  d3.select(this).classed("dragging", true);
	}

	function dragged(d) {
		d3.select(this).attr("cx", d.x = d3.event.x).attr("cy", d.y = d3.event.y);
		tick();
	}

	function dragended(d) {
	  d3.select(this).classed("dragging", false);
	}
	
	
	function setXY() {
		for (var i = 0; i < node.length; i++) {
			if (node[i].x == undefined) {
				node[i].x = w / 2;
				node[i].y = h / 2;
			}
		}
	}
	
	function linkNode() {
		links = [];
		for (var i = 0; i < node.length; i++) {
			if (node[i].child != undefined) {
				for (var y = 0; y < node[i].child.length; y++) {
					links.push({
						source: node[i],
		                target: node[node[i].child[y]]
					});
				}
			};
		};
	}
	
	function tick() {
		rect
			  .attr("cx", function(d) { return d.x; })
	          .attr("cy", function(d) { return d.y; });

		text
				.attr("dx", function(d) { return d.x; })
				.attr("dy", function(d) { return d.y; });
		
        line 
            .attr('x1', function(d){ return d.source.x; }) 
            .attr('y1', function(d){ return d.source.y; })
            .attr('x2', function(d){ return d.target.x; })
            .attr('y2', function(d){ return d.target.y; });
	}
	
	function reDraw() {
		var ts = g.attr("transform");
		
		setXY();
		linkNode();
		
		svg.remove();
		
		svg = d3.select("#main").append("svg")
											.attr("width", w)
											.attr("height", h);
		
		addg = svg.selectAll("g")
											.data(node)
											.enter()
												.append("g")
												.attr("transform", ts);
		g = svg.selectAll("g");
		
		rect = g.append("circle")
											.attr("r", function(d) { return d.values; })
											.attr("cx", function(d) { return d.x; })
	    									.attr("cy", function(d) { return d.y; });
		
		line = g.selectAll("line")
											.data(links)
											.enter()
											.append("line")
											.style("pointer-events", "none");
		
		force = d3.layout.force()
					    .nodes(node)
					    .links(links)
					    .size([w,h]);
		
		text = g.append("text")
				.attr("dx", function(d) { return d.x; })
				.attr("dy", function(d) { return d.y; })
				.text(function(d, i) { return d.text; });
		
		force.on("tick", tick);
		svg.call(zoom);
		rect.call(drag);
	    rect.on("click", clicked);
	    
	    tick();
	}
	
	function addData(text) {
		if (!isSelected()) {
			return;
		}
		node.push({
			"text" : text, "parent" : node.indexOf(selectednode), "x" : (selectednode.x + 150), "y" : (selectednode.y + 150), "values" : 100
		});
		if (selectednode.child != undefined) {
			selectednode.child.push(node.length-1); 
		} else {
			selectednode.child = [node.length-1];
		}
		selectednode = undefined;
		
		reDraw();
	}
	
	function editData(text, values) {
		if (!isSelected()) {
			return;
		}
		selectednode.text = text;
		selectednode.values = values;
		selectednode = undefined;
			
		reDraw();
	}
	
	function getNode() {
		return selectednode;
	}
	
	function deleteData() {
		if (!isSelected()) {
			return;
		}
		var index = node.indexOf(selectednode);
		var selected_child = undefined;
		if (node[index].child != undefined) {
			 selected_child = node[index].child; 
		} 
		
		// remove selectednode from parent
		node[selectednode.parent].child.splice(node[selectednode.parent].child.indexOf(index), 1);
		
		// function about delete childs
		var array_todelete = [];
		var delete_child = function(array_child) {
				for (var i in array_child) {
					if (node[array_child[i]].child != undefined) {
						delete_child(node[array_child[i]].child);
					}
					array_todelete.push(node.indexOf(node[array_child[i]]));
				}
		}
		if (selected_child != undefined) { delete_child(selected_child); }
		
		// delete list (sorted)
		array_todelete.push(index);
		array_todelete.sort(function(a,b) {
			return b - a;
		});
		
		
		// delete and reorder nodes
		for (var loop in array_todelete) {
			node.splice(array_todelete[loop], 1);
			
			for (var int = 0; int < node.length; int++) {
				if (node[int].parent > array_todelete[loop]) {
					node[int].parent = (node[int].parent - 1);
				}
				if (node[int].child != undefined) {
					for (var y = 0; y < node[int].child.length; y++) {
						if (node[int].child[y] > array_todelete[loop]) {
							node[int].child[y] = (node[int].child[y] - 1);
						}
					}
				}
			} 
		}
		
		selectednode = undefined;
		reDraw();
	}
	
	function isSelected() {
		var result = false;
		if (selectednode == undefined) {
			alert("먼저 생각을 선택해주세요.");
			result = false;
		} else {
			result = true;
		}
		return result;
	}
	
	function setViewpoint(node) {
		var xy = [];
		var t = undefined;
		if (arguments.length != 0) {
			xy = [node.x, node.y];
		} else {
			xy.push(0);
			xy.push(0);
		}
		t = "translate(" + xy +")scale(" + 1 + ")";	
		
		var zoom = d3.behavior.zoom()
		.scale("1")
		.translate(xy)
	    .scaleExtent(zoomrate)
	    .on("zoom", zoomed);
		
		var translate = [0, 0];
		g.transition()
			.duration(700)
			.attr("transform", t);
		
		svg.call(zoom);
		tick();
	}
	
	console.log($("#main").html());
	tick();