package io.github.datastructures;

import io.github.testcases.TestCases;
import io.github.datastructures.utils.BinaryTreePrinter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BinaryHeap_ {

    @Test
    @Parameters(method = "heapMinCases", source = TestCases.class)
    public void testMinHeap(int[] array, int[] expected) {

        List<Integer> values = new BinaryHeap(array, BinaryHeap.Type.MIN).asList().stream()
                .map(IBinaryNode::value)
                .collect(Collectors.toList());

        BinaryTreePrinter.printTree(new BinaryHeap(array, BinaryHeap.Type.MIN).getRoot());
        for(int i = 0; i < values.size(); i++) {
            assertThat(values.get(i)).isEqualTo(expected[i]);
        }
    }

    @Test
    @Parameters(method = "heapMaxCases", source = TestCases.class)
    public void testMaxHeap(int[] array, int[] expected) {

        List<Integer> values = new BinaryHeap(array, BinaryHeap.Type.MAX).asList().stream()
                .map(IBinaryNode::value)
                .collect(Collectors.toList());

        BinaryTreePrinter.printTree(new BinaryHeap(array, BinaryHeap.Type.MAX).getRoot());
        for(int i = 0; i < values.size(); i++) {
            assertThat(values.get(i)).isEqualTo(expected[i]);
        }
    }


    @Test
    @Parameters(method = "generalCases", source = TestCases.class)
    public void removeMaxHeapHead(int[] array) {

        List<IBinaryNode> removedNodes = removeAllNodes(new BinaryHeap(array, BinaryHeap.Type.MAX));

        IntStream.range(1, removedNodes.size())
                .forEach(i -> assertThat(removedNodes.get(i - 1).value()).isGreaterThan(removedNodes.get(i).value()));
    }

    @Test
    @Parameters(method = "generalCases", source = TestCases.class)
    public void removeMinHeapHead(int[] array) {

        List<IBinaryNode> removedNodes = removeAllNodes(new BinaryHeap(array, BinaryHeap.Type.MIN));

        IntStream.range(1, removedNodes.size())
                .forEach(i -> assertThat(removedNodes.get(i).value()).isGreaterThan(removedNodes.get(i - 1).value()));
    }

    @Test
    @Parameters(method = "insertCases", source = TestCases.class)
    public void insertMinHeapElement(int[] array, int toBeInserted) {

        BinaryHeap binaryHeap = new BinaryHeap(array, BinaryHeap.Type.MIN);
        binaryHeap.insert(toBeInserted);

        List<IBinaryNode> removedNodes = removeAllNodes(new BinaryHeap(array, BinaryHeap.Type.MIN));

        IntStream.range(1, removedNodes.size())
                .forEach(i -> assertThat(removedNodes.get(i).value()).isGreaterThan(removedNodes.get(i - 1).value()));
    }

    @Test
    @Parameters(method = "insertCases", source = TestCases.class)
    public void insertMaxHeapElement(int[] array, int toBeInserted) {

        BinaryHeap binaryHeap = new BinaryHeap(array, BinaryHeap.Type.MAX);
        binaryHeap.insert(toBeInserted);

        List<IBinaryNode> removedNodes = removeAllNodes(new BinaryHeap(array, BinaryHeap.Type.MAX));

        IntStream.range(1, removedNodes.size())
                .forEach(i -> assertThat(removedNodes.get(i - 1).value()).isGreaterThan(removedNodes.get(i).value()));
    }

    private List<IBinaryNode> removeAllNodes(BinaryHeap binaryHeap) {

        List<IBinaryNode> removedNodes = new ArrayList<>();

        IBinaryNode node;
        while((node = binaryHeap.removeHead()) != null) {
            removedNodes.add(node);
        }

        return removedNodes;
    }

}
