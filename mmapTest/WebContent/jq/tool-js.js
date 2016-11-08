/**
 * 
 */
	//var w = 900;
	//var h = 600;
	var w = window.outerWidth;
	var h = window.outerHeight;
	console.log(w);
	console.log(h);
	
	var circlefill = "rgba(118,214,255,0.7)";
	
	var rootfill = "rgba(255,119,142,1)",
		rootstroke = "rgba(204,198,65,0.5) !important",
    	rootstroke_width = "10 !important",
    	rootstroke_dasharray = "0 !important;";
    	
    var	linestroke = "black",
    	linestroke_width = "1",
    	lineshape_rendering = "crispEdges",
    	lineopacity = "0.1";
    
    var textfill = "black",
    	textfont_size = "20px",
		textfont_family = "맑은 고딕",
		textfont = "bolder";
	
	var zoomrate = [1/8, 8];
	
	var selected = null;
	var selectednode = null;
	
	var node = [
		            //{text : "1번타자", parent : 1, child : [1,2,3], values: 50}
		            {text : "루트 노드", root : true, values : 100}
		       ]
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
							.attr("width");
	var	height = svg
							.attr("height");
	var	addg = svg
							.selectAll("g")
							.data(node)
							.enter()
								.append("g")
								.attr("transform", "translate(0,0)");
	
	var g = svg.selectAll("g");
	
	var rect = g.append("circle")
										.attr("r", function(d) { return d.values; })
										.attr("id", function(d) { if(d.root) {return "circle-root";} })
										.attr("cx", function(d) { return d.x; })
    									.attr("cy", function(d) { return d.y; });
			
	var line = g.selectAll("line")
										.data(links)
										.enter()
										.append("line")
										.style("pointer-events", "none");
	
	var text = g.append("text")
			.attr("dx", function(d) { return (d.x - (d.values / 3)); })
			.attr("dy", function(d) { return (d.y - (d.values / 4)); })
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
    styleData();
	
	function clicked(d) {
		if (selected != d3.select(this)) {
			if (selected != undefined) {
				selected.classed("selected", false);
			}
			selected = d3.select(this);
			selectednode = d;
			d3.select(this).classed("selected", true);
			
			d3.selectAll(".selected-border").remove();
			d3.select(this.parentNode).append("circle")
	        .classed("selected-border", true)
	        .attr("cx", function(d) { return d.x; })
	        .attr("cy", function(d) { return d.y; })
	        .attr("r", function(d) { return (d.values + 50); })
	        	.transition()
			        .duration(750)
			        .attr("r", function(d) { return d.values; })
			        .style("stroke-width", 0)
			        .style("stroke-opacity", .2);
		}
	}
	
	function zoomed() {
		g.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
	}
	
	function dragstarted(d) {
		if (!d.root) {
		  d3.event.sourceEvent.stopPropagation();
		  d3.select(this).classed("dragging", true);
		}
	}

	function dragged(d) {
		if (!d.root) {
			d3.select(this).attr("cx", d.x = d3.event.x).attr("cy", d.y = d3.event.y);
			tick();
		}
	}

	function dragended(d) {
		if (!d.root) {
			d3.select(this).classed("dragging", false);
		}
	}
	
	
	function setXY() {
		console.log("setXY : " + node);
		for (var i = 0; i < node.length; i++) {
			if (node[i].x == undefined) {
				node[i].x = w / 2 - (node[i].values / 2);
				node[i].y = h / 2 - (node[i].values / 2);
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
				.attr("dx", function(d) { return (d.x - (d.values / 3)); })
				.attr("dy", function(d) { return (d.y - (d.values / 4)); });
		
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
											.attr("id", function(d) { if(d.root) {return "circle-root";} })
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
				.attr("dx", function(d) { return (d.x - (d.values / 3)); })
				.attr("dy", function(d) { return (d.y - (d.values / 4)); })
				.text(function(d, i) { return d.text; });
		
		force.on("tick", tick);
		svg.call(zoom);
		rect.call(drag);
	    rect.on("click", clicked);
	    
	    styleData();
	    tick();

	}
	
	function addData(text) {
		if (!isSelected()) {
			return;
		}
		node.push({
			"text" : text, "parent" : node.indexOf(selectednode), "x" : (selectednode.x + 150), "y" : (selectednode.y + 150), "values" : 70
		});
		if (selectednode.child != undefined) {
			selectednode.child.push(node.length-1); 
		} else {
			selectednode.child = [node.length-1];
		}
		selectednode = undefined;
		selected = undefined;
		
		reDraw();
	}
	
	function editData(text, values) {
		if (!isSelected()) {
			return;
		}
		selectednode.text = text;
		selectednode.values = parseInt(values);
		selectednode = undefined;
		selected = undefined;
			
		reDraw();
	}
	
	function styleData() {
		svg.selectAll("circle:not(#circle-root)").style("fill", function(d) {
			if (d.fill == undefined) {
				d.fill = circlefill;
				return circlefill;
			} else {
				return d.fill
			}
		});

		svg.selectAll("circle#circle-root").style("fill", function(d) {
			if (d.fill == undefined) {
				d.fill = rootfill;
				return rootfill;
			} else {
				return d.fill;
			}
		})
		.style("stroke", function(d) {
			if (d.stroke == undefined) {
				d.stroke = rootstroke;
				return rootstroke;
			} else {
				return d.stroke;
			}
		})
		.style("stroke-width", function(d) {
			if (d.stroke_width == undefined) {
				d.stroke_width = rootstroke_width;
				return rootstroke_width;
			} else {
				return d.stroke_width;
			}
		})
		.style("stroke-dasharray", function(d) {
			if (d.stroke_dasharray == undefined) {
				d.stroke_dasharray = rootstroke_dasharray;
				return rootstroke_dasharray;
			} else {
				return d.stroke_dasharray;
			}
		});

		g.selectAll("line")
		.style("stroke", function(d) {
			if (d.stroke == undefined) {
				d.stroke = linestroke;
				return linestroke;
			} else {
				return d.stroke;
			}
		})
		.style("stroke-width", function(d) {
			if (d.stroke_width == undefined) {
				d.stroke_width = linestroke_width;
				return linestroke_width;
			} else {
				return d.stroke_width;
			}
		})
		.style("shape-rendering", function(d) {
			if (d.shape_rendering == undefined) {
				d.shape_rendering = lineshape_rendering; 
				return lineshape_rendering;
			} else {
				return d.shape;
			}
		})
		.style("opacity", function(d) {
			if (d.opacity == undefined) {
				d.opacity = lineopacity;
				return lineopacity;
			} else {
				return d.opacity;
			}
		});

		g.append("text")
		.style("fill", function(d) {
			if (d.fill == undefined) {
				d.fill = textfill;
				return textfill;
			} else {
				return d.fill;
			}
		})
		.style("font-size", function(d) {
			if (d.font_size == undefined) {
				d.font_size = textfont_size;
				return textfont_size;
			} else {
				return d.font_size;
			}
		})
		.style("font-family", function(d) {
			if (d.font_family == undefined) {
				d.font_family = textfont_family; 
				return textfont_family;
			} else {
				return d.font_family;
			}
		})
		.style("font", function(d) {
			if (d.font == undefined) {
				d.font = textfont;
				return textfont;
			} else {
				return d.font;
			}
		});
	}
	
	function getNode() {
		return selectednode;
	}
	
	function deleteData() {
		if (!isSelected()) {
			return;
		}
		if (selectednode.root) {
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
	
	function isRoot() {
		return selectednode.root;
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
	
	tick();